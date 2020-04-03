package org.folio.service.manager.export;

import org.folio.rest.jaxrs.model.FileDefinition;
import org.folio.util.OkapiConnectionParams;

import java.util.List;

/**
 * Payload of the export manager request.
 * Contains necessary information needed to export a chunk of data.
 */
public class ExportPayload {
  private List<String> identifiers;
  private boolean last;
  private FileDefinition fileExportDefinition;
  private OkapiConnectionParams okapiConnectionParams;
  private String jobExecutionId;
  private int exportedRecordsNumber;

  public ExportPayload() {
  }

  public ExportPayload(List<String> identifiers, boolean last, FileDefinition fileExportDefinition, OkapiConnectionParams okapiConnectionParams, String jobExecutionId) {
    this.identifiers = identifiers;
    this.last = last;
    this.fileExportDefinition = fileExportDefinition;
    this.okapiConnectionParams = okapiConnectionParams;
    this.jobExecutionId = jobExecutionId;
  }

  public List<String> getIdentifiers() {
    return identifiers;
  }

  public void setIdentifiers(List<String> identifiers) {
    this.identifiers = identifiers;
  }

  public boolean isLast() {
    return last;
  }

  public void setLast(boolean last) {
    this.last = last;
  }

  public FileDefinition getFileExportDefinition() {
    return fileExportDefinition;
  }

  public void setFileExportDefinition(FileDefinition fileExportDefinition) {
    this.fileExportDefinition = fileExportDefinition;
  }

  public OkapiConnectionParams getOkapiConnectionParams() {
    return okapiConnectionParams;
  }

  public void setOkapiConnectionParams(OkapiConnectionParams okapiConnectionParams) {
    this.okapiConnectionParams = okapiConnectionParams;
  }

  public String getJobExecutionId() {
    return jobExecutionId;
  }

  public void setJobExecutionId(String jobExecutionId) {
    this.jobExecutionId = jobExecutionId;
  }

  public int getExportedRecordsNumber() {
    return exportedRecordsNumber;
  }

  public void setExportedRecordsNumber(int exportedRecordsNumber) {
    this.exportedRecordsNumber = exportedRecordsNumber;
  }
}