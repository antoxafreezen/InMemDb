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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lab.inmemdb.domain.Record;
import lab.inmemdb.service.RecordService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/databases/{dbId}/tables/{tbId}/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Record> getAllRecordsByTableId(@PathVariable Integer id) {
        return recordService.findAllByTableId(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Record> addRecordToTable(@PathVariable Integer dbId, @PathVariable Integer tbId,
                                                   @RequestBody Record record, UriComponentsBuilder ucBuilder) {
        Record newRecord = recordService.addRecordToTable(record, tbId);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setLocation(ucBuilder.path("/api/databases/{dbId}/tables/{tbId}/attributes/{attributeId}")
                .buildAndExpand(dbId, tbId, newRecord.getId()).toUri());
        return new ResponseEntity<>(newRecord, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public Record editRecordAttribute(@PathVariable Integer id, @RequestBody Record record) {
        return recordService.update(record);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTable(@PathVariable Integer id) {
        recordService.removeById(id);
    }
}
