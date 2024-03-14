package com.nanshuo.service.impl;

import com.nanshuo.entity.po.ProductInfo;
import com.nanshuo.entity.query.ProductInfoQuery;
import com.nanshuo.entity.query.SimplePage;
import com.nanshuo.entity.vo.PaginationResultVO;
import com.nanshuo.enums.PageSize;
import com.nanshuo.mappers.ProductInfoMapper;
import com.nanshuo.service.ProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 商品信息Service
 * @author: 南烁
 * @date: 2024/03/14
 */
@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductInfoMapper<ProductInfo, ProductInfoQuery> productInfoMapper;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<ProductInfo> findListByParam(ProductInfoQuery query) {
        return this.productInfoMapper.selectList(query);
    }


    /**
     * 根据条件查询数量
     */
    @Override
    public Integer findCountByParam(ProductInfoQuery query) {
        return this.productInfoMapper.selectCount(query);
    }

    /**
     * 分页查询
     */
    @Override
    public PaginationResultVO<ProductInfo> findListByPage(ProductInfoQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<ProductInfo> list = this.findListByParam(query);
        PaginationResultVO<ProductInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(ProductInfo bean) {
        return this.productInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<ProductInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.productInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或修改
     */
    @Override
    public Integer addOrUpdateBatch(List<ProductInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.productInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据Id查询
     */
    @Override
    public ProductInfo getProductInfoById(Integer id) {
        return this.productInfoMapper.selectById(id);
    }

    /**
     * 根据Id更新
     */
    @Override
    public Integer updateProductInfoById(ProductInfo bean, Integer id) {
        return this.productInfoMapper.updateById(bean, id);
    }

    /**
     * 根据Id删除
     */
    @Override
    public Integer deleteProductInfoById(Integer id) {
        return this.productInfoMapper.deleteById(id);
    }

    /**
     * 根据Code查询
     */
    @Override
    public ProductInfo getProductInfoByCode(String code) {
        return this.productInfoMapper.selectByCode(code);
    }

    /**
     * 根据Code更新
     */
    @Override
    public Integer updateProductInfoByCode(ProductInfo bean, String code) {
        return this.productInfoMapper.updateByCode(bean, code);
    }

    /**
     * 根据Code删除
     */
    @Override
    public Integer deleteProductInfoByCode(String code) {
        return this.productInfoMapper.deleteByCode(code);
    }

    /**
     * 根据SkuTypeAndColorType查询
     */
    @Override
    public ProductInfo getProductInfoBySkuTypeAndColorType(Integer skuType, Integer colorType) {
        return this.productInfoMapper.selectBySkuTypeAndColorType(skuType, colorType);
    }

    /**
     * 根据SkuTypeAndColorType更新
     */
    @Override
    public Integer updateProductInfoBySkuTypeAndColorType(ProductInfo bean, Integer skuType, Integer colorType) {
        return this.productInfoMapper.updateBySkuTypeAndColorType(bean, skuType, colorType);
    }

    /**
     * 根据SkuTypeAndColorType删除
     */
    @Override
    public Integer deleteProductInfoBySkuTypeAndColorType(Integer skuType, Integer colorType) {
        return this.productInfoMapper.deleteBySkuTypeAndColorType(skuType, colorType);
    }

}