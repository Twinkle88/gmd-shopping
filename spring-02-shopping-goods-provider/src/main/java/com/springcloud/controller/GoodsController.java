package com.springcloud.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;
import com.springcloud.vo.ResultValue;

/**
 * ��Ʒģ����Ʋ�
 * @author ��Ӧ��
 * ��select�Ӿ��г����˾ۺϺ�����ǾۺϺ�������ʱ������ʹ��group by�Ӿ�
        ��ʹ��group by�Ӿ�ʱ��select�Ӿ��зǾۺϺ������б��������group by�Ӿ���
   select c1.class1_name,count(g.goods_id) from class1 c1 join class2 c2 on c1.class1_id=c2.class1_id join goods g on c2.class2_id=g.class2_id group by c1.class1_name;
 *
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "/insert")
	public ResultValue insert(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			//����service��Ӧ�ķ�������µ���Ʒ��Ϣ�����ҵõ�����Ƿ�ɹ�
			Integer insert = this.goodsService.insert(goods);
			//�����ѯ�ɹ�
			if(insert > 0) {
				//���ý��״̬Ϊ0
				rv.setCode(0);
				rv.setMessage("��Ʒ��Ϣ¼��ɹ�");
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("��Ʒ��Ϣ¼��ʧ��");
		return rv;
	}
	
	@RequestMapping(value = "/select")
	public ResultValue select(Goods goods,@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			//��ѯ������������Ʒ��Ϣ
			PageInfo<Goods> pageInfo = this.goodsService.select(goods, pageNumber);
			//�ӷ�ҳ��Ϣ�л����Ʒ��Ϣ
			List<Goods> list = pageInfo.getList();
			//�����ѯ����������������Ʒ��Ϣ
			if(list != null && list.size() > 0) {
				
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				//����Ʒ��Ϣ��ָ���ļ����뼯����
				map.put("goodsList", list);
				
				//���÷�ҳ��Ϣ
				PageUtils pageUtils = new PageUtils(pageInfo.getPages() * PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);
				//����ҳ��Ϣ��ָ�������ִ���Map������
				map.put("pageUtils", pageUtils);
				
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("û���ҵ�������������Ʒ��Ϣ������");
		return rv;
	}
	@RequestMapping(value="/updateById")
	public ResultValue updateById(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			//����service��Ӧ�ķ����޸���Ʒ��Ϣ�����ҵõ��޸��Ƿ�ɹ�
			Integer update = this.goodsService.updateGoodsById(goods);
			//�����ѯ�ɹ�
			if(update > 0) {
				//���ý��״̬Ϊ0
				rv.setCode(0);
				rv.setMessage("��Ʒ��Ϣ�޸ĳɹ�������");
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("��Ʒ��Ϣ�޸�ʧ�ܣ�����");
		return rv;		
	}
	
	@RequestMapping(value="/update")
	public ResultValue update(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			//����service��Ӧ�ķ����޸���ƷͼƬ��Ϣ�����ҵõ��޸��Ƿ�ɹ�
			Integer update = this.goodsService.update(goods);
			//�����ѯ�ɹ�
			if(update > 0) {
				//���ý��״̬Ϊ0
				rv.setCode(0);
				rv.setMessage("��ƷͼƬ��Ϣ�޸ĳɹ�������");
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("��ƷͼƬ��Ϣ�޸�ʧ�ܣ�����");
		return rv;	
	}
	
	/**
	 * ��ѯ����ǰʮ����Ʒ��Ϣ
	 * @return
	 */
	@RequestMapping(value = "/selectGroup")
	public ResultValue selectGroup() {
		ResultValue rv = new ResultValue();
		try {
			List<Goods> list = this.goodsService.selectGroup();
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				//���������������ڱ�����״ͼ��x���y�������
				List<String> x = new ArrayList<>();
				List<Integer> y = new ArrayList<>();
				//����ѯ�Ľ������Ʒ���ƴ���x�У���������y��
				for (Goods goods : list) {
					x.add(goods.getGoodsName());
					y.add(goods.getGoodsSum());
				}
				//����һ��Map�������ڱ����ȡ����x���y�������
				Map<String, Object> map = new HashMap<>();
				map.put("x", x);
				map.put("y", y);
				//��Map������ӵ�ResultValue��
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("��ȡ����ʧ�ܣ�����");
		 return rv;
		
	}
}
