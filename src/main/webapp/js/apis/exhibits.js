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