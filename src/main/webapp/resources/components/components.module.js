import angular from 'angular';
import employee from './employee/employee.module';
import header from './header/header.module';
import location from './location/location.module';
import login from './login/login.module';
import popupRequired from './popupRequired/popupRequired.module';
import sidebar from './sidebar/sidebar.module';
import vendor from './vendor/vendor.module';
import popupAddNewEmployee from './popupAddNewEmployee/popupAddNewEmployee.module';
import popupCancel from './popupAddNewEmployee/popupCancel/popupCancel.module';
import database from './database/database.module';
import table from  './table/table.module';

const components = [
        employee,
        header,
        location,
        login,
        popupRequired,
        sidebar,
        vendor,
        popupAddNewEmployee,
        popupCancel,
        database,
        table
    ],
    componentsModule = angular.module('components', components);

export default componentsModule.name;