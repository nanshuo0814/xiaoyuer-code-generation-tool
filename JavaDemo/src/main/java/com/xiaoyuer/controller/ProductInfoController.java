package com.xiaoyuer.controller;

import com.xiaoyuer.entity.po.ProductInfo;
import com.xiaoyuer.entity.query.ProductInfoQuery;
import com.xiaoyuer.entity.vo.ResponseVO;
import com.xiaoyuer.service.ProductInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 商品信息Controller
 * @author: xiaoyuer
 * @date: 2023/11/05
 */
@RestController
@RequestMapping("/productInfo")
public class ProductInfoController extends ABaseController {

	@Resource
	private ProductInfoService productInfoService;

	/**
	 * 分页查询列表
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ProductInfoQuery query) {
		return getSuccessResponseVO(productInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO ResponseVO(@RequestBody ProductInfo bean) {
		this.productInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ProductInfo> listBean) {
		this.productInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<ProductInfo> listBean) {
		this.productInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询
	 */
	@RequestMapping("getProductInfoById")
	public ResponseVO getProductInfoById(Integer id) {
		return getSuccessResponseVO(this.productInfoService.getProductInfoById(id));
	}

	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateProductInfoById")
	public ResponseVO updateProductInfoById(@RequestBody ProductInfo bean, Integer id) {
		this.productInfoService.updateProductInfoById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteProductInfoById")
	public ResponseVO deleteProductInfoById(Integer id) {
		this.productInfoService.deleteProductInfoById(id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Code查询
	 */
	@RequestMapping("getProductInfoByCode")
	public ResponseVO getProductInfoByCode(String code) {
		return getSuccessResponseVO(this.productInfoService.getProductInfoByCode(code));
	}

	/**
	 * 根据Code更新
	 */
	@RequestMapping("updateProductInfoByCode")
	public ResponseVO updateProductInfoByCode(@RequestBody ProductInfo bean, String code) {
		this.productInfoService.updateProductInfoByCode(bean,code);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Code删除
	 */
	@RequestMapping("deleteProductInfoByCode")
	public ResponseVO deleteProductInfoByCode(String code) {
		this.productInfoService.deleteProductInfoByCode(code);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据SkuTypeAndColorType查询
	 */
	@RequestMapping("getProductInfoBySkuTypeAndColorType")
	public ResponseVO getProductInfoBySkuTypeAndColorType(Integer skuType, Integer colorType) {
		return getSuccessResponseVO(this.productInfoService.getProductInfoBySkuTypeAndColorType(skuType, colorType));
	}

	/**
	 * 根据SkuTypeAndColorType更新
	 */
	@RequestMapping("updateProductInfoBySkuTypeAndColorType")
	public ResponseVO updateProductInfoBySkuTypeAndColorType(@RequestBody ProductInfo bean, Integer skuType, Integer colorType) {
		this.productInfoService.updateProductInfoBySkuTypeAndColorType(bean,skuType, colorType);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据SkuTypeAndColorType删除
	 */
	@RequestMapping("deleteProductInfoBySkuTypeAndColorType")
	public ResponseVO deleteProductInfoBySkuTypeAndColorType(Integer skuType, Integer colorType) {
		this.productInfoService.deleteProductInfoBySkuTypeAndColorType(skuType, colorType);
		return getSuccessResponseVO(null);
	}

}