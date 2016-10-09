package lab.inmemdb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lab.inmemdb.domain.Database;
import lab.inmemdb.domain.Table;
import lab.inmemdb.service.TableService;
import lab.inmemdb.web.dto.TableDto;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/databases/{id}/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Table> getAllTablesByDbId(@PathVariable Integer id) {
        return tableService.findAllByDbId(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Table> addTable(@PathVariable Integer id, @RequestBody TableDto tableDto,
                                          UriComponentsBuilder ucBuilder) {
        Table newTable = tableService.addTableToDb(tableDto.getName(), id);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setLocation(ucBuilder.path("/api/databases/{id}/tables/{tableId}")
                .buildAndExpand(id, newTable.getId()).toUri());
        return new ResponseEntity<>(newTable, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public Table editTable(@PathVariable Integer id, @RequestBody TableDto tableDto) {
        Table updatedTable = new Table();
        updatedTable.setName(tableDto.getName());
        updatedTable.setId(id);
        return tableService.updateName(updatedTable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTable(@PathVariable Integer id){
        tableService.removeById(id);
    }
}
