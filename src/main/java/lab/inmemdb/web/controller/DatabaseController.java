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

import lab.inmemdb.domain.Database;
import lab.inmemdb.service.DatabaseService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/databases")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Database> addDatabase(@RequestBody Database database, UriComponentsBuilder ucBuilder) {
        Database newDb = databaseService.saveDatabase(database);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/api/databases/{id}").buildAndExpand(newDb.getId()).toUri());
        return new ResponseEntity<>(newDb, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Database> getAllDatabases() {
        return databaseService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public Database editDatabase(@PathVariable Integer id, @RequestBody Database database) {
        return databaseService.update(database);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDatabase(@PathVariable Integer id){
        databaseService.removeById(id);
    }
}
