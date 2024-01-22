/**
 * 获取添加字段
 */
export const getAddColumns = (columns) => {
    const arr = [];
    if (columns && columns.length > 0) {
        for (let i = 0; i < columns.length; i++) {
            const obj = columns[i];
            if (obj.isShowSave) {
                arr.push(obj);
            }
        }
    }
    return arr;
}

