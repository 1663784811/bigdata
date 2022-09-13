<!--  =============   添加  =========================== -->
<el-dialog :visible.sync="addParam" title="${__table__note__}" top="10vh" class="winBox" :close-on-click-modal="false">
<el-form  :model="tempParamData" label-position="left" class="pdaFormBox" label-width="150px" style="width: 100%;margin-top: 30px" inline>
  ${txVue.formItem}
</el-form>
<span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="save${__Table__}Data">保存</el-button>
      </span>
</el-dialog>