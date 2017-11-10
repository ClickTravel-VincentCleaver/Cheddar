/*
 * Copyright 2014 Click Travel Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.clicktravel.cheddar.rest.exception.mapper.cdm1;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.clicktravel.common.validation.ValidationException;
import com.clicktravel.schema.canonical.data.model.v1.common.ErrorResponse;
import com.clicktravel.schema.canonical.data.model.v1.common.ValidationError;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(final ValidationException ex) {
        final ErrorResponse errorResponse = new ErrorResponse();
        final ValidationError validationError = new ValidationError();
        for (final String field : ex.getFields()) {
            validationError.getField().add(field);
        }
        validationError.setError(ex.getMessage());
        errorResponse.getValidationErrors().add(validationError);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
