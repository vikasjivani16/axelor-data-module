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
package com.axelor.apps.base.web;

import com.axelor.apps.base.AxelorException;
import com.axelor.apps.base.service.exception.TraceBackService;
import com.axelor.apps.online.db.Cloth;
import com.axelor.apps.online.db.OrderLine;
import com.axelor.apps.online.db.ReturnOrderLine;
import com.axelor.apps.online.db.StockLine;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import java.math.BigDecimal;

public class ClothController {

  public void calculateTotalQty(ActionRequest request, ActionResponse response)
      throws AxelorException {
    try {
      Cloth cloth = request.getContext().asType(Cloth.class);

      BigDecimal stockQtyXs = new BigDecimal(0);
      BigDecimal stockQtyS = new BigDecimal(0);
      BigDecimal stockQtyM = new BigDecimal(0);
      BigDecimal stockQtyL = new BigDecimal(0);
      BigDecimal stockQtyXl = new BigDecimal(0);
      BigDecimal stockQtyXxl = new BigDecimal(0);

      BigDecimal orderQtyXs = new BigDecimal(0);
      BigDecimal orderQtyS = new BigDecimal(0);
      BigDecimal orderQtyM = new BigDecimal(0);
      BigDecimal orderQtyL = new BigDecimal(0);
      BigDecimal orderQtyXl = new BigDecimal(0);
      BigDecimal orderQtyXxl = new BigDecimal(0);

      BigDecimal returnQtyXs = new BigDecimal(0);
      BigDecimal returnQtyS = new BigDecimal(0);
      BigDecimal returnQtyM = new BigDecimal(0);
      BigDecimal returnQtyL = new BigDecimal(0);
      BigDecimal returnQtyXl = new BigDecimal(0);
      BigDecimal returnQtyXxl = new BigDecimal(0);

      BigDecimal totalQtyXs = new BigDecimal(0);
      BigDecimal totalQtyS = new BigDecimal(0);
      BigDecimal totalQtyM = new BigDecimal(0);
      BigDecimal totalQtyL = new BigDecimal(0);
      BigDecimal totalQtyXl = new BigDecimal(0);
      BigDecimal totalQtyXxl = new BigDecimal(0);

      for (StockLine line : cloth.getStockLine()) {
        if (line.getClothSize().equals("XS")) {
          stockQtyXs = stockQtyXs.add(line.getStockQty());
        }
        if (line.getClothSize().equals("S")) {
          stockQtyS = stockQtyS.add(line.getStockQty());
        }
        if (line.getClothSize().equals("M")) {
          stockQtyM = stockQtyM.add(line.getStockQty());
        }
        if (line.getClothSize().equals("L")) {
          stockQtyL = stockQtyL.add(line.getStockQty());
        }
        if (line.getClothSize().equals("XL")) {
          stockQtyXl = stockQtyXl.add(line.getStockQty());
        }
        if (line.getClothSize().equals("XXL")) {
          stockQtyXxl = stockQtyXxl.add(line.getStockQty());
        }
      }

      if (cloth.getOrderLine() != null) {
        for (OrderLine line : cloth.getOrderLine()) {
          if (line.getClothSize().equals("XS")) {
            orderQtyXs = orderQtyXs.add(line.getOrderQty());
          }
          if (line.getClothSize().equals("S")) {
            orderQtyS = orderQtyS.add(line.getOrderQty());
          }
          if (line.getClothSize().equals("M")) {
            orderQtyM = orderQtyM.add(line.getOrderQty());
          }
          if (line.getClothSize().equals("L")) {
            orderQtyL = orderQtyL.add(line.getOrderQty());
          }
          if (line.getClothSize().equals("XL")) {
            orderQtyXl = orderQtyXl.add(line.getOrderQty());
          }
          if (line.getClothSize().equals("XXL")) {
            orderQtyXxl = orderQtyXxl.add(line.getOrderQty());
          }
        }
      }

      if (cloth.getReturnOrderLine() != null) {
        for (ReturnOrderLine line : cloth.getReturnOrderLine()) {
          if (line.getClothSize().equals("XS")) {
            returnQtyXs = returnQtyXs.add(line.getReturnQty());
          }
          if (line.getClothSize().equals("S")) {
            returnQtyS = returnQtyS.add(line.getReturnQty());
          }
          if (line.getClothSize().equals("M")) {
            returnQtyM = returnQtyM.add(line.getReturnQty());
          }
          if (line.getClothSize().equals("L")) {
            returnQtyL = returnQtyL.add(line.getReturnQty());
          }
          if (line.getClothSize().equals("XL")) {
            returnQtyXl = returnQtyXl.add(line.getReturnQty());
          }
          if (line.getClothSize().equals("XXL")) {
            returnQtyXxl = returnQtyXxl.add(line.getReturnQty());
          }
        }
      }

      totalQtyXs = stockQtyXs.add(returnQtyXs).subtract(orderQtyXs);
      totalQtyS = stockQtyS.add(returnQtyS).subtract(orderQtyS);
      totalQtyM = stockQtyM.add(returnQtyM).subtract(orderQtyM);
      totalQtyL = stockQtyL.add(returnQtyL).subtract(orderQtyL);
      totalQtyXl = stockQtyXl.add(returnQtyXl).subtract(orderQtyXl);
      totalQtyXxl = stockQtyXxl.add(returnQtyXxl).subtract(orderQtyXxl);

      response.setValue("xsQtyOrder", orderQtyXs);
      response.setValue("sQtyOrder", orderQtyS);
      response.setValue("mQtyOrder", orderQtyM);
      response.setValue("lQtyOrder", orderQtyL);
      response.setValue("xlQtyOrder", orderQtyXl);
      response.setValue("xxlQtyOrder", orderQtyXxl);

      response.setValue("xsQtyReturn", returnQtyXs);
      response.setValue("sQtyReturn", returnQtyS);
      response.setValue("mQtyReturn", returnQtyM);
      response.setValue("lQtyReturn", returnQtyL);
      response.setValue("xlQtyReturn", returnQtyXl);
      response.setValue("xxlQtyReturn", returnQtyXxl);

      response.setValue("xsQtyStock", stockQtyXs);
      response.setValue("sQtyStock", stockQtyS);
      response.setValue("mQtyStock", stockQtyM);
      response.setValue("lQtyStock", stockQtyL);
      response.setValue("xlQtyStock", stockQtyXl);
      response.setValue("xxlQtyStock", stockQtyXxl);

      response.setValue("xsQtyTotal", totalQtyXs);
      response.setValue("sQtyTotal", totalQtyS);
      response.setValue("mQtyTotal", totalQtyM);
      response.setValue("lQtyTotal", totalQtyL);
      response.setValue("xlQtyTotal", totalQtyXl);
      response.setValue("xxlQtyTotal", totalQtyXxl);

    } catch (Exception e) {
      TraceBackService.trace(response, e);
    }
  }
}
