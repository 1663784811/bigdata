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
      <el-table size="mini" v-loading="listLoading" :data="tableList" border>
        <!--        -->
        <el-table-column type="selection" align="center" width="40"></el-table-column>
        <!--        -->

${txVue.tableColumn}

        <!--        -->
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
    <el-dialog :visible.sync="addParam" title="${__table__note__}" top="10vh" class="winBox" :close-on-click-modal="false">
      <el-form  :model="tempParamData" label-position="left" class="pdaFormBox" label-width="150px" style="width: 100%;margin-top: 30px" inline>

${txVue.formItem}

      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save${__Table__}Data">保存</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Pagination from '@/components/Pagination/index.vue'
import { ${__Table__}, TablePageType } from '@/api/types'
import { findPage${__Table__}, save${__Table__}, del${__Table__}, default${__Table__}Data } from '@/api/${__table__}'

@Component({
  name: 'financeIndex',
  components: {
    Pagination
  }
})
export default class extends Vue {
  private listLoading = false
  private tableList: ${__Table__}[] = []
  private tablePage: TablePageType ={
    page: 1,
    size: 20,
    total: 0
  }

  private addParam = false;
  private tempParamData:${__Table__} = default${__Table__}Data;

  created() {
    this.loadData()
  }

  /**
   * 点击编辑
   */
  private handleUpdate(row:${__Table__}, inx:number) {
    console.log('点击编辑')
    this.tempParamData = row
    this.addParam = true
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
    console.log('点击删除', row)
    this.$confirm('确认删除？').then(() => {
      del${__Table__}([row.id]).then((res:any) => {
        this.$message.success(res.msg ? `${'$'}{res.msg}` : `${'$'}{res}`)
        this.tableList.splice(inx, 1)
      })
    })
  }

  /**
   * 点击搜索
   */
  private clickSearch() {
    console.log('点击搜索')
    this.tablePage.page = 1
    this.loadData()
  }

  /**
   * 点击添加
   */
  private clickAdd() {
    this.addParam = true
    this.tempParamData = default${__Table__}Data
  }

  /**
   * 点击保存数据
   */
  private save${__Table__}Data() {
    console.log('点击保存数据')
    save${__Table__}(this.tempParamData).then((res:any) => {
      console.log('点击保存数据', res)
      this.tempParamData = res.data
      this.$message.success(`${'$'}{res.msg}`)
    })
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
   * 加载表格数据
   */
  private loadData() {
    this.listLoading = true
    findPage${__Table__}(this.tablePage).then((res:any) => {
      this.listLoading = false
      this.tableList = res.data
      this.tablePage.page = res.result.page
      this.tablePage.size = res.result.size
      this.tablePage.total = res.result.total
    }).catch((err: any) => {
      this.tableList = []
      console.log(err)
      this.listLoading = false
      this.$message.error(err.msg ? `${'$'}{err.msg}` : `${'$'}{err}`)
    })
  }
}

</script>
