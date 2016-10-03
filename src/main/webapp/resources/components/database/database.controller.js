class databaseController {
    constructor($scope, databaseService, $location) {
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
            databaseService.fetchAll('/api/databases').then((response) => {
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
            databaseService.addData('/api/databases', toPass).then((response) => {
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
            databaseService.updateData('/api/databases' + '/:documentId', this.editingObject).then((response) => {
                this.dataObject.list[key] = angular.copy(response);
                this.editingObject = {};
                this.editingId = null;
                this.style = '';
            }, () => {
                this.style = 'focusred';
            });
        };

        this.delete = (object) => {
            databaseService.deleteData('/api/databases' + '/:documentId', object);
            this.editingObject = {};
            this.editingId = null;
            self.fetchEverything();
        }

        this.goToTable = (id) => {
            $location.path('/databases/' + id + '/tables');
        }
    }
}

databaseController.$inject = ['$scope', 'databaseService', '$location'];

export default databaseController;