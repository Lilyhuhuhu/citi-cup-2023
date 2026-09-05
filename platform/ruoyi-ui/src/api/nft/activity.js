import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

//由卖方查询买方记录
export function BuyerListBySeller(userId) {
    return request({
      url: '/nft/activity/buyerlistbyseller',
      method: 'get',
      params: userId
    })
  }

//由卖方查询买方记录
export function SellerListByBuyer(userId) {
  return request({
    url: '/nft/activity/sellerlistbybuyer',
    method: 'get',
    params: userId
  })
}

//由卖方查询交易记录
export function ActivityListBySeller(userId) {
    return request({
      url: '/nft/activity/activitylistbyuserid',
      method: 'get',
      params: userId
    })
  }