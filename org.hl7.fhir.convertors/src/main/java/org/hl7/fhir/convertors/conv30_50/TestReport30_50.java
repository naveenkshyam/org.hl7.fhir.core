package org.hl7.fhir.convertors.conv30_50;

import org.hl7.fhir.convertors.VersionConvertor_30_50;
import org.hl7.fhir.exceptions.FHIRException;
import java.util.Collections;

public class TestReport30_50 {

    public static org.hl7.fhir.dstu3.model.TestReport convertTestReport(org.hl7.fhir.r5.model.TestReport src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.dstu3.model.TestReport tgt = new org.hl7.fhir.dstu3.model.TestReport();
        VersionConvertor_30_50.copyDomainResource(src, tgt);
        if (src.hasIdentifier())
            tgt.setIdentifier(VersionConvertor_30_50.convertIdentifier(src.getIdentifier()));
        if (src.hasNameElement())
            tgt.setNameElement((org.hl7.fhir.dstu3.model.StringType) VersionConvertor_30_50.convertType(src.getNameElement()));
        if (src.hasStatus())
            tgt.setStatus(convertTestReportStatus(src.getStatus()));
        if (src.hasTestScript())
            tgt.setTestScript(VersionConvertor_30_50.convertReference(src.getTestScript()));
        if (src.hasResult())
            tgt.setResult(convertTestReportResult(src.getResult()));
        if (src.hasScoreElement())
            tgt.setScoreElement((org.hl7.fhir.dstu3.model.DecimalType) VersionConvertor_30_50.convertType(src.getScoreElement()));
        if (src.hasTesterElement())
            tgt.setTesterElement((org.hl7.fhir.dstu3.model.StringType) VersionConvertor_30_50.convertType(src.getTesterElement()));
        if (src.hasIssuedElement())
            tgt.setIssuedElement((org.hl7.fhir.dstu3.model.DateTimeType) VersionConvertor_30_50.convertType(src.getIssuedElement()));
        if (src.hasParticipant()) {
            for (org.hl7.fhir.r5.model.TestReport.TestReportParticipantComponent t : src.getParticipant()) tgt.addParticipant(convertTestReportParticipantComponent(t));
        }
        if (src.hasSetup())
            tgt.setSetup(convertTestReportSetupComponent(src.getSetup()));
        if (src.hasTest()) {
            for (org.hl7.fhir.r5.model.TestReport.TestReportTestComponent t : src.getTest()) tgt.addTest(convertTestReportTestComponent(t));
        }
        if (src.hasTeardown())
            tgt.setTeardown(convertTestReportTeardownComponent(src.getTeardown()));
        return tgt;
    }

    public static org.hl7.fhir.r5.model.TestReport convertTestReport(org.hl7.fhir.dstu3.model.TestReport src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.r5.model.TestReport tgt = new org.hl7.fhir.r5.model.TestReport();
        VersionConvertor_30_50.copyDomainResource(src, tgt);
        if (src.hasIdentifier())
            tgt.setIdentifier(VersionConvertor_30_50.convertIdentifier(src.getIdentifier()));
        if (src.hasNameElement())
            tgt.setNameElement((org.hl7.fhir.r5.model.StringType) VersionConvertor_30_50.convertType(src.getNameElement()));
        if (src.hasStatus())
            tgt.setStatus(convertTestReportStatus(src.getStatus()));
        if (src.hasTestScript())
            tgt.setTestScript(VersionConvertor_30_50.convertReference(src.getTestScript()));
        if (src.hasResult())
            tgt.setResult(convertTestReportResult(src.getResult()));
        if (src.hasScoreElement())
            tgt.setScoreElement((org.hl7.fhir.r5.model.DecimalType) VersionConvertor_30_50.convertType(src.getScoreElement()));
        if (src.hasTesterElement())
            tgt.setTesterElement((org.hl7.fhir.r5.model.StringType) VersionConvertor_30_50.convertType(src.getTesterElement()));
        if (src.hasIssuedElement())
            tgt.setIssuedElement((org.hl7.fhir.r5.model.DateTimeType) VersionConvertor_30_50.convertType(src.getIssuedElement()));
        if (src.hasParticipant()) {
            for (org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantComponent t : src.getParticipant()) tgt.addParticipant(convertTestReportParticipantComponent(t));
        }
        if (src.hasSetup())
            tgt.setSetup(convertTestReportSetupComponent(src.getSetup()));
        if (src.hasTest()) {
            for (org.hl7.fhir.dstu3.model.TestReport.TestReportTestComponent t : src.getTest()) tgt.addTest(convertTestReportTestComponent(t));
        }
        if (src.hasTeardown())
            tgt.setTeardown(convertTestReportTeardownComponent(src.getTeardown()));
        return tgt;
    }

    public static org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantComponent convertTestReportParticipantComponent(org.hl7.fhir.r5.model.TestReport.TestReportParticipantComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantComponent tgt = new org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantComponent();
        VersionConvertor_30_50.copyElement(src, tgt);
        if (src.hasType())
            tgt.setType(convertTestReportParticipantType(src.getType()));
        if (src.hasUriElement())
            tgt.setUriElement((org.hl7.fhir.dstu3.model.UriType) VersionConvertor_30_50.convertType(src.getUriElement()));
        if (src.hasDisplayElement())
            tgt.setDisplayElement((org.hl7.fhir.dstu3.model.StringType) VersionConvertor_30_50.convertType(src.getDisplayElement()));
        return tgt;
    }

    public static org.hl7.fhir.r5.model.TestReport.TestReportParticipantComponent convertTestReportParticipantComponent(org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.r5.model.TestReport.TestReportParticipantComponent tgt = new org.hl7.fhir.r5.model.TestReport.TestReportParticipantComponent();
        VersionConvertor_30_50.copyElement(src, tgt);
        if (src.hasType())
            tgt.setType(convertTestReportParticipantType(src.getType()));
        if (src.hasUriElement())
            tgt.setUriElement((org.hl7.fhir.r5.model.UriType) VersionConvertor_30_50.convertType(src.getUriElement()));
        if (src.hasDisplayElement())
            tgt.setDisplayElement((org.hl7.fhir.r5.model.StringType) VersionConvertor_30_50.convertType(src.getDisplayElement()));
        return tgt;
    }

    static public org.hl7.fhir.r5.model.TestReport.TestReportParticipantType convertTestReportParticipantType(org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantType src) throws FHIRException {
        if (src == null)
            return null;
        switch(src) {
            case TESTENGINE:
                return org.hl7.fhir.r5.model.TestReport.TestReportParticipantType.TESTENGINE;
            case CLIENT:
                return org.hl7.fhir.r5.model.TestReport.TestReportParticipantType.CLIENT;
            case SERVER:
                return org.hl7.fhir.r5.model.TestReport.TestReportParticipantType.SERVER;
            default:
                return org.hl7.fhir.r5.model.TestReport.TestReportParticipantType.NULL;
        }
    }

    static public org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantType convertTestReportParticipantType(org.hl7.fhir.r5.model.TestReport.TestReportParticipantType src) throws FHIRException {
        if (src == null)
            return null;
        switch(src) {
            case TESTENGINE:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantType.TESTENGINE;
            case CLIENT:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantType.CLIENT;
            case SERVER:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantType.SERVER;
            default:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportParticipantType.NULL;
        }
    }

    static public org.hl7.fhir.r5.model.TestReport.TestReportResult convertTestReportResult(org.hl7.fhir.dstu3.model.TestReport.TestReportResult src) throws FHIRException {
        if (src == null)
            return null;
        switch(src) {
            case PASS:
                return org.hl7.fhir.r5.model.TestReport.TestReportResult.PASS;
            case FAIL:
                return org.hl7.fhir.r5.model.TestReport.TestReportResult.FAIL;
            case PENDING:
                return org.hl7.fhir.r5.model.TestReport.TestReportResult.PENDING;
            default:
                return org.hl7.fhir.r5.model.TestReport.TestReportResult.NULL;
        }
    }

    static public org.hl7.fhir.dstu3.model.TestReport.TestReportResult convertTestReportResult(org.hl7.fhir.r5.model.TestReport.TestReportResult src) throws FHIRException {
        if (src == null)
            return null;
        switch(src) {
            case PASS:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportResult.PASS;
            case FAIL:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportResult.FAIL;
            case PENDING:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportResult.PENDING;
            default:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportResult.NULL;
        }
    }

    public static org.hl7.fhir.r5.model.TestReport.TestReportSetupComponent convertTestReportSetupComponent(org.hl7.fhir.dstu3.model.TestReport.TestReportSetupComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.r5.model.TestReport.TestReportSetupComponent tgt = new org.hl7.fhir.r5.model.TestReport.TestReportSetupComponent();
        VersionConvertor_30_50.copyElement(src, tgt);
        if (src.hasAction()) {
            for (org.hl7.fhir.dstu3.model.TestReport.SetupActionComponent t : src.getAction()) tgt.addAction(VersionConvertor_30_50.convertSetupActionComponent(t));
        }
        return tgt;
    }

    public static org.hl7.fhir.dstu3.model.TestReport.TestReportSetupComponent convertTestReportSetupComponent(org.hl7.fhir.r5.model.TestReport.TestReportSetupComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.dstu3.model.TestReport.TestReportSetupComponent tgt = new org.hl7.fhir.dstu3.model.TestReport.TestReportSetupComponent();
        VersionConvertor_30_50.copyElement(src, tgt);
        if (src.hasAction()) {
            for (org.hl7.fhir.r5.model.TestReport.SetupActionComponent t : src.getAction()) tgt.addAction(VersionConvertor_30_50.convertSetupActionComponent(t));
        }
        return tgt;
    }

    static public org.hl7.fhir.r5.model.TestReport.TestReportStatus convertTestReportStatus(org.hl7.fhir.dstu3.model.TestReport.TestReportStatus src) throws FHIRException {
        if (src == null)
            return null;
        switch(src) {
            case COMPLETED:
                return org.hl7.fhir.r5.model.TestReport.TestReportStatus.COMPLETED;
            case INPROGRESS:
                return org.hl7.fhir.r5.model.TestReport.TestReportStatus.INPROGRESS;
            case WAITING:
                return org.hl7.fhir.r5.model.TestReport.TestReportStatus.WAITING;
            case STOPPED:
                return org.hl7.fhir.r5.model.TestReport.TestReportStatus.STOPPED;
            case ENTEREDINERROR:
                return org.hl7.fhir.r5.model.TestReport.TestReportStatus.ENTEREDINERROR;
            default:
                return org.hl7.fhir.r5.model.TestReport.TestReportStatus.NULL;
        }
    }

    static public org.hl7.fhir.dstu3.model.TestReport.TestReportStatus convertTestReportStatus(org.hl7.fhir.r5.model.TestReport.TestReportStatus src) throws FHIRException {
        if (src == null)
            return null;
        switch(src) {
            case COMPLETED:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportStatus.COMPLETED;
            case INPROGRESS:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportStatus.INPROGRESS;
            case WAITING:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportStatus.WAITING;
            case STOPPED:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportStatus.STOPPED;
            case ENTEREDINERROR:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportStatus.ENTEREDINERROR;
            default:
                return org.hl7.fhir.dstu3.model.TestReport.TestReportStatus.NULL;
        }
    }

    public static org.hl7.fhir.dstu3.model.TestReport.TestReportTeardownComponent convertTestReportTeardownComponent(org.hl7.fhir.r5.model.TestReport.TestReportTeardownComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.dstu3.model.TestReport.TestReportTeardownComponent tgt = new org.hl7.fhir.dstu3.model.TestReport.TestReportTeardownComponent();
        VersionConvertor_30_50.copyElement(src, tgt);
        if (src.hasAction()) {
            for (org.hl7.fhir.r5.model.TestReport.TeardownActionComponent t : src.getAction()) tgt.addAction(VersionConvertor_30_50.convertTeardownActionComponent(t));
        }
        return tgt;
    }

    public static org.hl7.fhir.r5.model.TestReport.TestReportTeardownComponent convertTestReportTeardownComponent(org.hl7.fhir.dstu3.model.TestReport.TestReportTeardownComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.r5.model.TestReport.TestReportTeardownComponent tgt = new org.hl7.fhir.r5.model.TestReport.TestReportTeardownComponent();
        VersionConvertor_30_50.copyElement(src, tgt);
        if (src.hasAction()) {
            for (org.hl7.fhir.dstu3.model.TestReport.TeardownActionComponent t : src.getAction()) tgt.addAction(VersionConvertor_30_50.convertTeardownActionComponent(t));
        }
        return tgt;
    }

    public static org.hl7.fhir.dstu3.model.TestReport.TestReportTestComponent convertTestReportTestComponent(org.hl7.fhir.r5.model.TestReport.TestReportTestComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.dstu3.model.TestReport.TestReportTestComponent tgt = new org.hl7.fhir.dstu3.model.TestReport.TestReportTestComponent();
        VersionConvertor_30_50.copyElement(src, tgt);
        if (src.hasNameElement())
            tgt.setNameElement((org.hl7.fhir.dstu3.model.StringType) VersionConvertor_30_50.convertType(src.getNameElement()));
        if (src.hasDescriptionElement())
            tgt.setDescriptionElement((org.hl7.fhir.dstu3.model.StringType) VersionConvertor_30_50.convertType(src.getDescriptionElement()));
        if (src.hasAction()) {
            for (org.hl7.fhir.r5.model.TestReport.TestActionComponent t : src.getAction()) tgt.addAction(VersionConvertor_30_50.convertTestActionComponent(t));
        }
        return tgt;
    }

    public static org.hl7.fhir.r5.model.TestReport.TestReportTestComponent convertTestReportTestComponent(org.hl7.fhir.dstu3.model.TestReport.TestReportTestComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.r5.model.TestReport.TestReportTestComponent tgt = new org.hl7.fhir.r5.model.TestReport.TestReportTestComponent();
        VersionConvertor_30_50.copyElement(src, tgt);
        if (src.hasNameElement())
            tgt.setNameElement((org.hl7.fhir.r5.model.StringType) VersionConvertor_30_50.convertType(src.getNameElement()));
        if (src.hasDescriptionElement())
            tgt.setDescriptionElement((org.hl7.fhir.r5.model.StringType) VersionConvertor_30_50.convertType(src.getDescriptionElement()));
        if (src.hasAction()) {
            for (org.hl7.fhir.dstu3.model.TestReport.TestActionComponent t : src.getAction()) tgt.addAction(VersionConvertor_30_50.convertTestActionComponent(t));
        }
        return tgt;
    }
}