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
package com.axelor.apps.businessproduction.service;

import com.axelor.apps.production.db.SaleOrderLineDetails;
import com.axelor.apps.production.service.SaleOrderLineBomSyncService;
import com.axelor.apps.production.service.SaleOrderLineDetailsBomSyncServiceImpl;
import com.axelor.apps.sale.db.SaleOrderLine;
import com.google.inject.Inject;

public class SaleOrderLineDetailsBomSyncBusinessProductionServiceImpl
    extends SaleOrderLineDetailsBomSyncServiceImpl {

  @Inject
  public SaleOrderLineDetailsBomSyncBusinessProductionServiceImpl(
      SaleOrderLineBomSyncService saleOrderLineBomSyncService) {
    super(saleOrderLineBomSyncService);
  }

  @Override
  public void syncSaleOrderLineDetailsBom(SaleOrderLineDetails saleOrderLineDetails) {
    SaleOrderLine projectSaleOrderLine = saleOrderLineDetails.getProjectSaleOrderLine();
    if (projectSaleOrderLine != null) {
      saleOrderLineBomSyncService.removeSolDetailsBomLine(
          projectSaleOrderLine.getProjectSaleOrderLineDetailsList(),
          projectSaleOrderLine.getBillOfMaterial());
    } else {
      super.syncSaleOrderLineDetailsBom(saleOrderLineDetails);
    }
  }
}
