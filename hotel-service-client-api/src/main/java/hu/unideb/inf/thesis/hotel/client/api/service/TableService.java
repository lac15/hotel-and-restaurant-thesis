package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.TableVo;

public interface TableService {

    TableVo saveTable(TableVo tableVo);

    TableVo getTableById(Long id);

    TableVo getTableByNumber(int number);

    TableVo getTableBySeats(int seats);

    void setTableReservedByNumber(int number, boolean reserved);

}
