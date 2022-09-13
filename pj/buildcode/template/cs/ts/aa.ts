// 查询参数
private listQuery ={
${typeScriptType.parameter}
    createTimeORDER: { operator: "order", field: "create_time", value: "", alias: "t", dataType: "number" },
    limit: {page: 1, limit: 20},
};