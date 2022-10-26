const company = {
  tableCompany: {
    column: [
      {
        title: 'tid',
        key: 'tid',
        type: 'selection',
      },
      {
        title: '公司名称',
        key: 'name',
      },
      {
        title: '股票名称',
        key: 'stock_name',
      },
      {
        title: '股票类型',
        key: 'stock_type',
      },
      {
        title: '股票号',
        key: 'stock_no',
      },
      {
        title: '标签',
        key: 'tags',
      },
    ],
  },
  department: {
    column: [
      {
        label: '东部地区',
        key: 1,
        children: [
          {
            label: '总裁部',
            key: 11,
          },
          {
            label: '财务部',
            key: 12,
          },
          {
            label: '技术部',
            key: 13,
          },
          {
            label: '销售部',
            key: 14,
          },
        ],
      },
      {
        label: '西部地区',
        key: 2,
        children: [
          {
            label: '总裁部',
            key: 21,
          },
          {
            label: '财务部',
            key: 22,
          },
          {
            label: '技术部',
            key: 23,
          },
          {
            label: '销售部',
            key: 24,
          },
        ],
      },
      {
        label: '南部地区',
        key: 3,
        children: [
          {
            label: '总裁部',
            key: 31,
          },
          {
            label: '财务部',
            key: 32,
          },
          {
            label: '技术部',
            key: 33,
          },
          {
            label: '销售部',
            key: 34,
          },
        ],
      },
      {
        label: '北部地区',
        key: 4,
        children: [
          {
            label: '总裁部',
            key: 41,
          },
          {
            label: '财务部',
            key: 42,
          },
          {
            label: '技术部',
            key: 43,
          },
          {
            label: '销售部',
            key: 44,
          },
        ],
      },
    ],
  },
}

export default company
