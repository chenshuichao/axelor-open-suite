/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2005-2025 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.axelor.apps.base.rest;

import com.axelor.apps.base.service.user.UserPermissionResponseComputeService;
import com.axelor.auth.AuthUtils;
import com.axelor.auth.db.User;
import com.axelor.inject.Beans;
import com.axelor.utils.api.HttpExceptionHandler;
import com.axelor.utils.api.ResponseConstructor;
import io.swagger.v3.oas.annotations.Operation;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/aos/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRestController {
  @Operation(
      summary = "Get user permissions",
      tags = {"User"})
  @Path("/permissions")
  @GET
  @HttpExceptionHandler
  public Response getPermissions() {
    User user = AuthUtils.getUser();
    return ResponseConstructor.build(
        Response.Status.OK,
        Beans.get(UserPermissionResponseComputeService.class).computeUserPermissionResponse(user));
  }

  @Operation(
      summary = "Get user meta permissions",
      tags = {"User"})
  @Path("/meta-permission-rules")
  @GET
  @HttpExceptionHandler
  public Response getMetaPermissions() {
    User user = AuthUtils.getUser();
    return ResponseConstructor.build(
        Response.Status.OK,
        Beans.get(UserPermissionResponseComputeService.class)
            .computeUserMetaPermissionRuleResponse(user));
  }
}
