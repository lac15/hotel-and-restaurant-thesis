package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.TableVo;

import java.util.List;

public interface TableService {

    TableVo saveTable(TableVo tableVo);

    List<TableVo> getTables();

    TableVo getTableById(Long id);

    TableVo getTableByNumber(int number);

}
