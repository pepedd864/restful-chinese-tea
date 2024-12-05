export default function (file) {
    return new Promise((resolve, reject) => {
        if (!file || !file instanceof File) reject('请上传文件')
        const reader = new FileReader()
        reader.readAsArrayBuffer(file)
        reader.onload = function (e) {
            const fileStream = e.target.result
            const arrayBuffer = fileStream.buffer;
            resolve(arrayBuffer)
        }
    })
}