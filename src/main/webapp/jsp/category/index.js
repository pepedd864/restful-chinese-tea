export function getListItem(contextPath,item) {
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
编辑 删除
</td>
`
}
