package com.yunchao.hsh.controller.admin;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.model.HshSelfItem;
import com.yunchao.hsh.service.ISelfItemService;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Api
@Controller
@RequestMapping("/page/admin/self/item/")
public class SelfItemController extends BaseController {

    @Autowired
    private ISelfItemService selfItemService;

    /**
     * 商品列表
     *
     * @return
     */
    @ApiOperation(value = "自营商品列表", tags = "自营商品列表")
    @RequestMapping(value = {"list"})
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "条数", dataType = "int"),
    })
    public Result getItemList(HttpServletRequest request) {
        int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ParamUtils.getIntParameter(request, "pageSize", 10);
        String name = ParamUtils.getParameter(request, "name", "");
        Result result = this.selfItemService.getItemList(pageNum, pageSize, name);
        return result;
    }


    @ApiOperation(value = "添加自营商品", tags = "添加自营商品")
    @RequestMapping(value = {"save"}, method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "desc", value = "商品描述", dataType = "String"),
            @ApiImplicitParam(name = "itemName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "itemImg", value = "商品图片", dataType = "String"),
            @ApiImplicitParam(name = "sellPoint", value = "商品简介", dataType = "String"),
            @ApiImplicitParam(name = "itemPrice", value = "商品价格", dataType = "String"),
            @ApiImplicitParam(name = "itemIntegral", value = "商品积分", dataType = "long"),
            @ApiImplicitParam(name = "itemUnit", value = "商品单位", dataType = "long"),
            @ApiImplicitParam(name = "itemNum", value = "商品数量", dataType = "int"),
    })
    public String addItem(HshSelfItem item, String desc) {
//    public String addItem(HttpServletRequest request, HshSelfItem item, String desc, @RequestParam MultipartFile[] file) {
        /*String imgsStr = "";
        for (int i = 0; i < file.length; i++) {
            if (file[i].getSize() > 0) {
                if (file[i].getSize() > 0) {
                    com.yunchao.hsh.utils.superdir.sub.Result result = CommonUtil.uploadPic(file[i], request, null);
                    if (!result.isSuccess()) {
                        result.setMessage("返回上传图片路径错误");
                        result.setSuccess(false);
                        return "/self/list";
                    }
                    Object[] obj = (Object[]) result.getData();
                    imgsStr = imgsStr + obj[1].toString() + ";";
                }
            }
        }
        item.setItemImg(imgsStr);*/
        this.selfItemService.addItem(item, desc);
        return "/self/list";
    }

    @ApiOperation(value = "修改自营商品", tags = "修改自营商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemId", value = "商品ID", dataType = "int"),
            @ApiImplicitParam(name = "desc", value = "商品描述", dataType = "String"),
            @ApiImplicitParam(name = "itemName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "itemImg", value = "商品图片", dataType = "String"),
            @ApiImplicitParam(name = "sellPoint", value = "商品简介", dataType = "String"),
            @ApiImplicitParam(name = "itemPrice", value = "商品价格", dataType = "String"),
            @ApiImplicitParam(name = "itemIntegral", value = "商品积分", dataType = "long"),
            @ApiImplicitParam(name = "itemUnit", value = "商品单位", dataType = "long"),
            @ApiImplicitParam(name = "itemNum", value = "商品数量", dataType = "int"),
    })
    @RequestMapping(value = {"update"}, method = RequestMethod.POST)
    public String updateItem(HshSelfItem item, String desc) {
//    public String updateItem(HttpServletRequest request, HshSelfItem item, String desc, @RequestParam MultipartFile[] file, String imgsStr) {
        /*for (int i = 0; i < file.length; i++) {
            if (file[i].getSize() > 0) {
                if (file[i].getSize() > 0) {
                    com.yunchao.hsh.utils.superdir.sub.Result result = CommonUtil.uploadPic(file[i], request, null);
                    if (!result.isSuccess()) {
                        result.setMessage("返回上传图片路径错误");
                        result.setSuccess(false);
                        return "/self/list";
                    }
                    Object[] obj = (Object[]) result.getData();
                    imgsStr = imgsStr + obj[1].toString() + ";";
                }
            }
        }
        item.setItemImg(imgsStr);*/
        this.selfItemService.updateItem(item, desc);
        return "/self/list";
    }


    @ApiOperation(value = "删除/下架自营商品", tags = "删除/下架自营商品")
    @RequestMapping(value = {"del"}, method = RequestMethod.GET)
    @ApiImplicitParam(name = "itemId", value = "商品ID", paramType = "query", dataType = "int")
    @ResponseBody
    public Result delItem(Long itemId) {
        Result result = this.selfItemService.delItem(itemId);
        return result;
    }

    @RequestMapping(value = {"findById"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result findByItemId(String itemId) {
        if (StringUtils.isNotBlank(itemId)) {
            HshSelfItem resp = this.selfItemService.findByItemId(itemId);
            return Result.ok(resp);
        } else {
            return Result.build("商品不存在");
        }
    }
}
