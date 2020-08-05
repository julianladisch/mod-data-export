package org.folio.service.transformationfields.builder;

import org.folio.rest.jaxrs.model.TransformationField;
import org.folio.rest.jaxrs.model.TransformationField.RecordType;
import org.folio.service.transformationfields.TransformationFieldsConfig;

/**
 * Builder is responsible to build jsonPath of the {@link TransformationField}.
 */
public interface PathBuilder {

  /**
   * Build the jsonPath of the {@link TransformationField}
   *
   * @param recordType                 record type of the field
   * @param transformationFieldsConfig {@link TransformationFieldsConfig}
   * @return string with the jsonPath result
   */
  String build(RecordType recordType, TransformationFieldsConfig transformationFieldsConfig);

  /**
   * Build the jsonPath of the {@link TransformationField} with reference data
   *
   * @param recordType                 record type of the field
   * @param transformationFieldsConfig {@link TransformationFieldsConfig}
   * @param referenceDataId            id of the reference data
   * @return string with the jsonPath result
   */
  String build(RecordType recordType, TransformationFieldsConfig transformationFieldsConfig, String referenceDataId);

}