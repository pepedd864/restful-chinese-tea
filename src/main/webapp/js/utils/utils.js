export function transformFileToBuffer(file) {
  return new Promise((resolve, reject) => {
    if (!file || !file instanceof File) reject('请上传文件')
    const reader = new FileReader()
    reader.onload = function (e) {
      // const fileStream = reader.result
      // const arrayBuffer = fileStream.buffer;
      // console.log({arrayBuffer})
      resolve(reader.result)
    }
    reader.onerror = function (error) {
      console.error('文件读取出错：', error);
      reject('文件读取失败，请检查文件是否有效');
    };
    reader.readAsArrayBuffer(file)
  })
}
