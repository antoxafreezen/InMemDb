import angular from 'angular';
import databaseService from './database.service';
import databaseComponent from './database.component';

const databaseModule = angular.module('database', [])
                              .service('databaseService', databaseService)
                              .component('databaseComponent', databaseComponent);

export default databaseModule.name;