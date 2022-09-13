<template>
  <div>
    <!-- ==========   搜索框   ==========   -->
    <div class="searchBox">
${txVue.searchItem}
      <!--  搜索按钮   -->
      <div class="searchItem">
        <el-button class="searchBtn " icon="el-icon-search" size="mini" @click="clickSearch">搜索</el-button>
        <el-button  type="primary" icon="el-icon-edit" size="mini" @click="clickAdd">添加</el-button>
        <el-button  type="danger" icon="el-icon-delete" size="mini" @click="clickDelSelect">删除</el-button>
      </div>
    </div>
    <!--  ==================   表格  ==================  -->
    <div class="tableBox">
      <el-table size="mini" v-loading="tableLoading" :data="tableList" border  @sort-change="sortChange">
        <el-table-column type="selection" align="center" width="40"></el-table-column>
${txVue.tableColumn}
        <el-table-column label="操作" align="center" width="230">
          <template slot-scope="{row,$index}">
            <el-button type="primary" size="mini" @click="handleUpdate(row,$index)">编辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(row, $index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pageBox">
        <el-pagination
          background
          layout="sizes, total,prev,pager, next, jumper"
          :page-size.sync="tablePage.size"
          :total="tablePage.total"
          @size-change="sizeChange"
          @current-change="currentChange"
        ></el-pagination>
      </div>
    </div>
    <!--  =============   添加  =========================== -->
    <el-dialog :visible.sync="saveParam" title="${__table__note__}" top="10vh" class="winBox" :close-on-click-modal="false">
      <el-form  :model="tempParamData" label-position="left" class="pdaFormBox" label-width="150px" style="width: 100%;margin-top: 30px" inline>
${txVue.formItem}
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save${__Table__}Data" :loading="saveLoading">保存</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Pagination from '@/components/Pagination/index.vue'
import { cloneDeep } from 'lodash'
import { TablePageType, ${__Table__} } from '@/api/types'
import { findPage${__Table__}, save${__Table__}, del${__Table__}, default${__Table__}Data } from '@/api/${__table__}'

@Component({
  name: '${__Table__}',
  components: {
    Pagination
  }
})
export default class extends Vue {
  //= ==表格数据
  private tableLoading = false
  private tableList: ${__Table__}[] = []
  private listQuery:any = {};
  private tablePage: TablePageType ={
    page: 1,
    size: 20,
    total: 0,
    sort: ''
  }

  //= === 保存数据
  private saveLoading = false;
  private saveParam = false;
  private tempParamData:${__Table__} = default${__Table__}Data;

  created() {
    this.loadData()
  }

  /**
   * 点击编辑
   */
  private handleUpdate(row:${__Table__}, inx:number) {
    this.tempParamData = cloneDeep(row)
    this.saveParam = true
  }

  /**
   * 点击添加
   */
  private clickAdd() {
    this.saveParam = true
    this.tempParamData = cloneDeep(default${__Table__}Data)
  }

  /**
   * 点击保存数据
   */
  private save${__Table__}Data() {
    const ${__table__}Data: ${__Table__} = this.tempParamData
    this.saveLoading = true
    save${__Table__}(${__table__}Data).then((res:any) => {
      this.tempParamData = res.data
      this.$notify({ title: '成功', message: '保存成功', type: 'success', duration: 2000 })
      this.saveLoading = false
      this.loadData()
    }).catch((err:any) => {
      this.saveLoading = false
      console.log(err)
    })
  }

  /**
   * 点击删除选择
   */
  private clickDelSelect() {
    console.log('点击删除选择')
  }

  /**
   * 点击删除
   */
  private handleDelete(row:${__Table__}, inx:number) {
    this.$confirm('确认删除？').then(() => {
      del${__Table__}([row.id]).then((res:any) => {
        this.$message.success('删除成功')
        this.tableList.splice(inx, 1)
      })
    })
  }

  /**
   * 点击搜索
   */
  private clickSearch() {
    this.tablePage.page = 1
    this.loadData()
  }

  /**
   * 点击页码
   */
  private currentChange(page:number) {
    this.loadData()
  }

  /**
   * 改变页码
   */
  private sizeChange(size:number) {
    this.tablePage.page = 1
    this.loadData()
  }

  /**
   * 排序改变
   */
  private sortChange(sort:any) {
    if (sort.prop) {
      if (sort.order === 'ascending') {
        this.tablePage.sort = `${'$'}{sort.prop}_asc`
      } else {
        this.tablePage.sort = `${'$'}{sort.prop}_desc`
      }
    } else {
      this.tablePage.sort = ''
    }
    this.loadData()
  }

  /**
   * 加载表格数据
   */
  private loadData() {
    this.tableLoading = true
    findPage${__Table__}({
      jsonStr: JSON.stringify(this.listQuery),
      page:this.tablePage.page,
      size:this.tablePage.size,
      sort:this.tablePage.sort
    }).then((res:any) => {
      this.tableLoading = false
      this.tableList = res.data
      this.tablePage.page = res.result.page
      this.tablePage.size = res.result.size
      this.tablePage.total = res.result.total
    }).catch((err: any) => {
      this.tableList = []
      console.log(err)
      this.tableLoading = false
      this.$message.error(err.msg ? `${'$'}{err.msg}` : `${'$'}{err}`)
    })
  }
}

</script>
