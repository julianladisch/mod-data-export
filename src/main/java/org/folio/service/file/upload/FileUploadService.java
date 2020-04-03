package org.folio.service.file.upload;

import io.vertx.core.Future;
import org.folio.rest.jaxrs.model.FileDefinition;
import org.folio.service.file.storage.FileStorage;

/**
 * File upload service. Provides lifecycle methods for file uploading functionality.
 */
public interface FileUploadService {
  /**
   * Starts uploading for the given {@link FileDefinition} by id
   *
   * @param fileDefinitionId id of the {@link FileDefinition}
   * @param tenantId         tenant id
   * @return {@link FileDefinition}
   */
  Future<FileDefinition> startUploading(String fileDefinitionId, String tenantId);

  /**
   * Saves given file data to the {@link FileStorage}
   *
   * @param fileDefinition {@link FileDefinition}
   * @param data           array of file bytes
   * @param tenantId       tenant id
   * @return {@link FileDefinition}
   */
  Future<FileDefinition> saveFileChunk(FileDefinition fileDefinition, byte[] data, String tenantId);

  /**
   * Completes uploading for the given {@link FileDefinition}
   *
   * @param fileDefinition {@link FileDefinition}
   * @param tenantId       tenant id
   * @return {@link FileDefinition}
   */
  Future<FileDefinition> completeUploading(FileDefinition fileDefinition, String tenantId);

  /**
   * Aborts uploading for the given {@link FileDefinition} by id
   *
   * @param fileDefinitionId id of the {@link FileDefinition}
   * @param tenantId         tenant id
   * @return {@link FileDefinition}
   */
  Future<FileDefinition> errorUploading(String fileDefinitionId, String tenantId);
}