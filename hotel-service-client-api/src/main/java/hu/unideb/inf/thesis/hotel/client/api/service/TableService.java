package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.ReservedTimeVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.TableVo;

import java.util.List;

public interface TableService {

    TableVo saveTable(TableVo tableVo);

    List<TableVo> getTables();

    void addReservedTimeToTable(TableVo tableVo, ReservedTimeVo reservedTimeVo);

    TableVo getTableById(Long id);

    TableVo getTableByNumber(int number);

}
