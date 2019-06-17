package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.Goods;

/**
 * ��Ʒģ�Ͳ�Ľӿڣ����ڶ�����Ʒģ��ķ���
 * @author ��Ӧ��
 *
 */
public interface GoodsService {

	/**
	 * ����µ���Ʒ��Ϣ
	 * @param goods	����Ʒ��Ϣ
	 * @return	�ɹ����ش���0�����������򷵻�0
	 */
	public abstract Integer insert(Goods goods);
	
	/**
	 * ��ѯ������������Ʒ��Ϣ
	 * @param goods	��ѯ����
	 * @param pageNumber ҳ��
	 * @return	�ɹ�����com.github.pagehelper.PageInfo���͵�ʵ�������򷵻�null
	 */
	public abstract PageInfo<Goods> select(Goods goods, Integer pageNumber);
	/**
	 * ���������޸���Ʒ��Ϣ
	 * @param goods ��ѯ����
	 * @return �ɹ����ش���0�����������򷵻�С�ڻ����0����
	 */
	public abstract Integer updateGoodsById(Goods goods);
	
	public abstract Integer update(Goods goods);
	
	 /**
     * ��ѯ����ǰʮ����Ʒ��Ϣ
     * @return	�ɹ�����java.util.List���͵�ʵ�������򷵻�null
     */
	public abstract List<Goods> selectGroup();
}
