package com.ruoyi.web.controller.nft;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.nft.domain.User;
import com.ruoyi.nft.service.IUserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.nft.domain.Activity;
import com.ruoyi.nft.service.IActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author GuYue
 * @date 2023-03-15
 */
@RestController
@RequestMapping("/nft/activity")
public class ActivityController extends BaseController
{
    @Autowired
    private IActivityService activityService;

    @Autowired
    private IUserService userService;

    /**
     * 获取用户洗钱金额
     */
    @PreAuthorize("@ss.hasPermi('user:illeguser:list')")
    @GetMapping("/usermoney")
    public AjaxResult getMoneyByUserId(Long userId){
        int count = activityService.getMoneyByUserId(userId);
        return AjaxResult.success(count);
    }

    /**
     * 获取平台洗钱金额
     */
    @PreAuthorize("@ss.hasPermi('user:illeguser:list')")
    @GetMapping("/nftmoney")
    public AjaxResult getMoneyByNftId(Long nftId){
        int count = activityService.getMoneyByNftid(nftId);
        return AjaxResult.success(count);
    }

    /**
     * 获取交易数通过交易时间held
     */
    @PreAuthorize("@ss.hasPermi('user:illeguser:list')")
    @GetMapping("/count")
    public AjaxResult getCount(@DateTimeFormat(pattern = "yyyy-MM-dd") Date held){
        int count = activityService.getCountByHeld(held);
        return AjaxResult.success(count);
    }

    /**
     * 查询【BuyerListBySeller】列表
     */
    @PreAuthorize("@ss.hasPermi('user:illeguser:list')")
    @GetMapping("/buyerlistbyseller")
    public TableDataInfo buyerList(Long seller)
    {
        startPage();
        List<Long> list = activityService.selectBuyerListBySeller(seller);
        //去重
        list = new ArrayList<>(new HashSet<>(list));
        List<User> userList = new ArrayList<>();
        for (Long var : list) {
            userList.add(userService.selectUserByUserId(var));
        }
        return getDataTable(userList);
    }

    /**
     * 查询【SellerListByBuyer】列表
     */
    @PreAuthorize("@ss.hasPermi('user:illeguser:list')")
    @GetMapping("/sellerlistbybuyer")
    public TableDataInfo sellerList(Long buyer)
    {
        startPage();
        List<Long> list = activityService.selectSellerListByBuyer(buyer);
        //去重
        list = new ArrayList<>(new HashSet<>(list));
        List<User> userList = new ArrayList<>();
        for (Long var : list) {
            userList.add(userService.selectUserByUserId(var));
        }
        return getDataTable(userList);
    }

    /**
     * 查询【ActivityListByUserId】列表
     */
    @PreAuthorize("@ss.hasPermi('user:illeguser:list')")
    @GetMapping("/activitylistbyuserid")
    public TableDataInfo activityList(Long userId)
    {
        startPage();
        List<Activity> slist = activityService.selectActivityListBySeller(userId);
        List<Activity> blist = activityService.selectActivityListByBuyer(userId);
        List<Activity> list = new ArrayList<>();
        list.addAll(slist); list.addAll(blist);
        return getDataTable(list);
    }

//    /**
//     * 查询【ActivityListBySeller】列表
//     */
//    @PreAuthorize("@ss.hasPermi('nft:activity:activityList')")
//    @GetMapping("/activitylistbyseller")
//    public TableDataInfo activityLists(Long seller)
//    {
//        startPage();
//        List<Activity> list = activityService.selectActivityListBySeller(seller);
//        return getDataTable(list);
//    }

//    /**
//     * 查询【ActivityListByBuyer】列表
//     */
//    @PreAuthorize("@ss.hasPermi('nft:activity:activityList')")
//    @GetMapping("/activitylistbybuyer")
//    public TableDataInfo activityListb(Long buyer)
//    {
//        startPage();
//        List<Activity> list = activityService.selectActivityListByBuyer(buyer);
//        return getDataTable(list);
//    }

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('nft:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(Activity activity)
    {
        startPage();
        List<Activity> list = activityService.selectActivityList(activity);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('nft:activity:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Activity activity)
    {
        List<Activity> list = activityService.selectActivityList(activity);
        ExcelUtil<Activity> util = new ExcelUtil<Activity>(Activity.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('nft:activity:query')")
    @GetMapping(value = "/{activityId}")
    public AjaxResult getInfo(@PathVariable("activityId") Long activityId)
    {
        return success(activityService.selectActivityByActivityId(activityId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('nft:activity:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Activity activity)
    {
        return toAjax(activityService.insertActivity(activity));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('nft:activity:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Activity activity)
    {
        return toAjax(activityService.updateActivity(activity));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('nft:activity:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable Long[] activityIds)
    {
        return toAjax(activityService.deleteActivityByActivityIds(activityIds));
    }
}
