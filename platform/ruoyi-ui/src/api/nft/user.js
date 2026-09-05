import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

//查询洗钱用户列表
export function listillUser(query) {
    return request({
      url: '/nft/user/list',
      method: 'get',
      params: query
    })
  }

// 查询用户详细
export function getillUser(userId) {
  return request({
    url: '/nft/user/' + parseStrEmpty(userId),
    method: 'get'
  })
}