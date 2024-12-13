export function createExhibits(contextPath, data) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: contextPath + '/api/exhibits/create',
            type: 'post',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (response) {
                resolve(response)
            },
            error: function (xhr, status, error) {
                reject(error)
            }
        })
    })
}

// exhibitId
export function deleteExhibits(contextPath, data) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: contextPath + '/api/exhibits/delete',
            type: 'delete',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (response) {
                resolve(response)
            },
            error: function (xhr, status, error) {
                reject(error)
            }
        })
    })
}

export function updateExhibits(contextPath, data) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: contextPath + '/api/exhibits/update',
            type: 'post',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (response) {
                resolve(response)
            },
            error: function (xhr, status, error) {
                reject(error)
            }
        })
    })
}

// export function updateExhibits(contextPath, data) {
//     return new Promise((resolve, reject) => {
//         $.ajax({
//             url: contextPath + '/api/exhibits/update',
//             type: 'post',
//             data: JSON.stringify(data),
//             contentType: 'application/json',
//             success: function (response) {
//                 resolve(response)
//             },
//             error: function (xhr, status, error) {
//                 reject(error)
//             }
//         })
//     })
// }

// categoryId
export function getByCategoryId(contextPath, data) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: contextPath + `/api/exhibits/get/exList/${data}`,
            type: 'get',
            contentType: 'application/json',
            success: function (response) {
                resolve(response)
            },
            error: function (xhr, status, error) {
                reject(error)
            }
        })
    })
}