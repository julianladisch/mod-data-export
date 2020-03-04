package org.folio.service.manager.exportmanager;

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

  public ExportPayload() {
  }

  public ExportPayload(List<String> identifiers, boolean last, FileDefinition fileExportDefinition, OkapiConnectionParams okapiConnectionParams) {
    this.identifiers = identifiers;
    this.last = last;
    this.fileExportDefinition = fileExportDefinition;
    this.okapiConnectionParams = okapiConnectionParams;
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
}