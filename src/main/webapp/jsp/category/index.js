export function getListItem(contextPath, item) {
    return `
<td>
<img src="${contextPath + item.icon}" />
</td>
<td>
${item.num}
</td>
<td>
${item.title}
</td>
<td>
<button class="upload-btn">上传</button>
<button class="edit-btn">编辑</button>
删除
</td>
`
}