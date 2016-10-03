class tableService {
    constructor($resource, $q, host) {
        this.resourceString = host.resourceString;
        let generateResource = (address) => {
            return $resource(this.resourceString + address, {}, {
                'get': {
                    method: 'GET',
                    transformResponse: (data) => {
                        return angular.fromJson(data).list
                    },
                    isArray: true
                },
                'save': {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-Requested-With': 'XMLHttpRequest'
                    }
                },
                'update': {
                    method: 'PUT',
                    params: {
                        documentId: '@documentId'
                    }
                },
                'delete': {
                    method: 'DELETE',
                    transformRequest: angular.identity,
                    params: {
                        documentId: '@documentId'
                    }
                }
            });
        };

        this.fetchAll = (address) => {
            let resource = generateResource(address),
                deferred = $q.defer();
            resource.query().$promise.then((response) => {
                deferred.resolve(JSON.parse(JSON.stringify(response)));
            }, () => {
                deferred.reject('error');
            });
            return deferred.promise;
        };

        this.addData = (address, object) => {
            let resource = generateResource(address, object),
                deferred = $q.defer();
            resource.save(object).$promise.then((response) => {
                deferred.resolve(JSON.parse(JSON.stringify(response)));
            }, () => {
                deferred.reject('error');
            });
            return deferred.promise;
        };

        this.updateData = (address, object) => {
            let resource = generateResource(address),
                deferred = $q.defer();
            resource.update({documentId: object.id}, object).$promise.then((response) => {
                console.log(response);
                deferred.resolve(JSON.parse(JSON.stringify(response)));
            }, () => {
                deferred.reject('error');
            });
            return deferred.promise;
        };

        this.deleteData = (address, object) => {
            let resource = generateResource(address),
                deferred = $q.defer();
            resource.delete({documentId: object.id}).$promise.then((response) => {
                deferred.resolve(JSON.parse(JSON.stringify(response)));
            }, () => {
                deferred.reject('error');
            });
            return deferred.promise;
        }
    }
}

tableService.$inject = ['$resource', '$q', 'host'];

export  default tableService;