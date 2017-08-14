package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.TableTypeVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.TableVo;

import java.util.List;

public interface TableTypeService {

    List<TableTypeVo> getTableTypes();

    TableTypeVo saveTableType(TableTypeVo TableTypeVo);

    TableTypeVo getTableTypeById(Long id);

    TableTypeVo getTableTypeBySeats(int seats);

    List<TableVo> getTablesByTableTypeId(Long TableTypeId);

}
