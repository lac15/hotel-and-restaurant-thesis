package hu.unideb.inf.thesis.hotel.web.managedbeans.reserve;

import hu.unideb.inf.thesis.hotel.client.api.service.TableService;
import hu.unideb.inf.thesis.hotel.client.api.vo.TableVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "reserveTableMB")
public class ReserveTableMB {

    @EJB
    private TableService tableService;

    private List<TableVo> tables = new ArrayList<>();

    @PostConstruct
    public void init() {
        tables.addAll(tableService.getTables());
    }

    public List<TableVo> getTables() {
        return tables;
    }

    public void setTables(List<TableVo> tables) {
        this.tables = tables;
    }
}
