import angular from 'angular';
import tableService from './table.service';
import tableComponent from './table.component';

const tableModule = angular.module('table', [])
                              .service('tableService', tableService)
                              .component('tableComponent', tableComponent);

export default tableModule.name;