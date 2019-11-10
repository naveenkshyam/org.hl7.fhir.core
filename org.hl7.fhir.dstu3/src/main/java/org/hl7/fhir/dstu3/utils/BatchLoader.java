package org.hl7.fhir.dstu3.utils;

/*-
 * #%L
 * org.hl7.fhir.dstu3
 * %%
 * Copyright (C) 2014 - 2019 Health Level 7
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hl7.fhir.dstu3.formats.IParser;
import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.Bundle.BundleType;
import org.hl7.fhir.dstu3.model.Bundle.HTTPVerb;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.utils.client.FHIRToolingClient;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;

public class BatchLoader {

  public static void main(String[] args) throws IOException, Exception {
    if (args.length < 3) {
      System.out.println("Batch uploader takes 3 parameters in order: server base url, file/folder to upload, and batch size");
    } else {
      String server = args[0];
      String file = args[1];
      int size = Integer.parseInt(args[2]);
      if (file.endsWith(".xml")) {
        throw new FHIRException("Unimplemented file type "+file);
      } else if (file.endsWith(".json")) {
        throw new FHIRException("Unimplemented file type "+file);
//      } else if (file.endsWith(".zip")) {
//        LoadZipFile(server, file, p, size, 0, -1);
      } else if (new File(file).isDirectory()) {
        LoadDirectory(server, file, size);
      } else 
        throw new FHIRException("Unknown file type "+file);
    }
  }

  private static void LoadDirectory(String server, String folder, int size) throws IOException, Exception {
    System.out.print("Connecting to "+server+".. ");
    FHIRToolingClient client = new FHIRToolingClient(server);
    System.out.println("Done");
    
    IniFile ini = new IniFile(Utilities.path(folder, "batch-load-progress.ini"));
    for (File f : new File(folder).listFiles()) {
      if (f.getName().endsWith(".json") || f.getName().endsWith(".xml")) {
        if (!ini.getBooleanProperty("finished", f.getName())) {
          sendFile(client, f, size, ini);
        }
      }
    }
  }

  
  private static void sendFile(FHIRToolingClient client, File f, int size, IniFile ini) throws FHIRFormatError, FileNotFoundException, IOException {
    long ms = System.currentTimeMillis();
    System.out.print("Loading "+f.getName()+".. ");
    IParser parser = f.getName().endsWith(".json") ? new JsonParser() : new XmlParser();
    Resource res = parser.parse(new FileInputStream(f));
    System.out.println("  done: ("+Long.toString(System.currentTimeMillis()-ms)+" ms)");
    
    if (res instanceof Bundle) {
      Bundle bnd = (Bundle) res;
      int cursor = ini.hasProperty("progress", f.getName()) ? ini.getIntegerProperty("progress", f.getName()) : 0;
      while (cursor < bnd.getEntry().size()) {
        Bundle bt = new Bundle();
        bt.setType(BundleType.BATCH);     
        bt.setId(UUID.randomUUID().toString().toLowerCase());
        for (int i = cursor; i < Math.min(bnd.getEntry().size(), cursor+size); i++) {
          BundleEntryComponent be = bt.addEntry();
          be.setResource(bnd.getEntry().get(i).getResource());
          be.getRequest().setMethod(HTTPVerb.PUT);
          be.getRequest().setUrl(be.getResource().getResourceType().toString()+"/"+be.getResource().getId());
        }
        System.out.print(f.getName()+" ("+cursor+"/"+bnd.getEntry().size()+"): ");
        ms = System.currentTimeMillis();
        Bundle resp = client.transaction(bt);

        int ncursor = cursor+size;
        for (int i = 0; i < resp.getEntry().size(); i++) {
          BundleEntryComponent t = resp.getEntry().get(i);
          if (!t.getResponse().getStatus().startsWith("2")) { 
            System.out.println("failed status at "+Integer.toString(i)+": "+t.getResponse().getStatus());
            ncursor = cursor+i-1;
            break;
          }
        }
        cursor = ncursor;
        System.out.println("  .. done: ("+Long.toString(System.currentTimeMillis()-ms)+" ms) "+SimpleDateFormat.getInstance().format(new Date()));
        ini.setIntegerProperty("progress", f.getName(), cursor, null);
        ini.save();
      }
      ini.setBooleanProperty("finished", f.getName(), true, null);
      ini.save();
    } else {
      client.update(res);
      ini.setBooleanProperty("finished", f.getName(), true, null);
      ini.save();
    }    
  }
//
//  private static void LoadZipFile(String server, String file, IParser p, int size, int start, int end) throws IOException, Exception {
//    System.out.println("Load Zip file "+file);
//    Bundle b = new Bundle();
//    b.setType(BundleType.COLLECTION);
//    b.setId(UUID.randomUUID().toString().toLowerCase());
//    ZipInputStream zip = new ZipInputStream(new FileInputStream(file));
//    ZipEntry entry;
//    while((entry = zip.getNextEntry())!=null)
//    {
//      try {
//        Resource r = p.parse(zip);
//        b.addEntry().setResource(r);
//      } catch (Exception e) {
//        throw new Exception("Error parsing "+entry.getName()+": "+e.getMessage(), e);
//      }
//    }
//    loadBundle(server, b, size, start, end);
//  }
//
//  
//  private static int loadBundle(String server, Bundle b, int size, int start, int end) throws URISyntaxException {
//    System.out.println("Post to "+server+". size = "+Integer.toString(size)+", start = "+Integer.toString(start)+", total = "+Integer.toString(b.getEntry().size()));
//    FHIRToolingClient client = new FHIRToolingClient(server);
//    int c = start;
//    if (end == -1)
//      end = b.getEntry().size();
//    while (c < end) {
//      Bundle bt = new Bundle();
//      bt.setType(BundleType.BATCH);     
//      bt.setId(UUID.randomUUID().toString().toLowerCase());
//      for (int i = c; i < Math.min(b.getEntry().size(), c+size); i++) {
//        BundleEntryComponent be = bt.addEntry();
//        be.setResource(b.getEntry().get(i).getResource());
//        be.getRequest().setMethod(HTTPVerb.PUT);
//        be.getRequest().setUrl(be.getResource().getResourceType().toString()+"/"+be.getResource().getId());
//      }
//      System.out.print("  posting..");
//      long ms = System.currentTimeMillis();
//      Bundle resp = client.transaction(bt);
//      
//      for (int i = 0; i < resp.getEntry().size(); i++) {
//        BundleEntryComponent t = resp.getEntry().get(i);
//        if (!t.getResponse().getStatus().startsWith("2")) { 
//          System.out.println("failed status at "+Integer.toString(i)+": "+t.getResponse().getStatus());
//          return c+i;
//        }
//      }
//      c = c + size;
//      System.out.println("  ..done: "+Integer.toString(c)+". ("+Long.toString(System.currentTimeMillis()-ms)+" ms)");
//    }
//    System.out.println(" done");
//    return c;
//  }

}
