/*
 *  Copyright 2015 - 2016 John D. Ament
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.
 *
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package ws.ament.hammock.cqrs.mapping;

import ws.ament.hammock.cqrs.domain.ViolationException;
import ws.ament.hammock.cqrs.domain.Violations;
import ws.ament.hammock.cqrs.stereotype.Mapper;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Mapper
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Inject
    private ExceptionMapper<ViolationException> violationExceptionExceptionMapper;
    @Override
    public Response toResponse(ConstraintViolationException e) {
        return violationExceptionExceptionMapper.toResponse(new ViolationException(new Violations(e.getConstraintViolations())));
    }
}
