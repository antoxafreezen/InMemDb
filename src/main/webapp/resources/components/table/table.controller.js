class tableController {
    constructor($scope, tableService, $location, $routeParams) {
        let self = this;
        this.editingId = null;
        this.newObject = {};
        this.editingObject = {};
        this.dataObject = {};
        this.trigered = false;
        this.style = '';
        this.regex = /\S/;
        this.regexNumber = /^([1-9]|[1-4]\d|50)$/;
        this.regexName = /^\w+$/;
        this.addObjectInProcess = false;

        this.checkStyle = (data) => {
            if(!data) {
                return this.style;
            } else {
                return '';
            }
        };

        $scope.$watch(() => {
        }, (newValue) => {
            self.fetchEverything();
        });

        self.fetchEverything = () => {
            tableService.fetchAll('/api/databases/' + $routeParams.id + '/tables').then((response) => {
                this.dataObject.list = response;
            }, () => {
                console.error('Error while fetching food');
            });
        };

        this.addingObject = () => {
            this.cancel();
            this.addObjectInProcess = true;
        };

        this.addToList = (value) => {
            let toPass = (angular.isDefined(value)) ? value : this.newObject;
            tableService.addData('/api/databases/' + $routeParams.id + '/tables', toPass).then((response) => {
                this.dataObject.list.push(response);
                this.newObject = {};
                this.style = '';
                this.addObjectInProcess = false;
            }, () => {
                //this.clear();
                this.style = 'focusred';
            });
        };

        this.clear = () => {
            this.addObjectInProcess = false;
            this.newObject = {};
            this.style = '';
        };

        this.editing = (object) => {
            this.addObjectInProcess = false;
            this.clear();
            this.editingObject = angular.copy(object);
            this.editingId = object.id;
        };

        this.cancel = () => {
            this.editingId = null;
        };

        this.editObject = (key) => {
            tableService.updateData('/api/databases/' + $routeParams.id + '/tables/:documentId', this.editingObject).then((response) => {
                this.dataObject.list[key] = angular.copy(response);
                this.editingObject = {};
                this.editingId = null;
                this.style = '';
            }, () => {
                this.style = 'focusred';
            });
        };

        this.delete = (object) => {
            tableService.deleteData('/api/databases/' + $routeParams.id + '/tables/:documentId', object);
            this.editingObject = {};
            this.editingId = null;
            self.fetchEverything();
        }

        this.goToTable = () => {
            $location.path('/databases/' + $routeParams.id + '/tables');
        }
    }
}

tableController.$inject = ['$scope', 'tableService', '$location', '$routeParams'];

export default tableController;