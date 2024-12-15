export function uploadFile(fileName, buffer) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: contextPath + '/api/file/upload?fileName=' + fileName,
            type: 'post',
            contentType: 'application/octet-stream',
            data: buffer,
            processData: false,
            success: function (response) {
                resolve(response)
            },
            error: function (xhr, status, error) {
                reject(error)
            }
        })
    })
}
