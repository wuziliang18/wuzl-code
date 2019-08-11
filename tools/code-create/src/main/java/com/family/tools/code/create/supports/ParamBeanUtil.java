package com.family.tools.code.create.supports;

import com.family.util.ConfigUtil;

public class ParamBeanUtil {
	public static String getName(ParamBean bean, String baseName) {
		if (bean != null && bean.getPrefix() != null
				&& !"".equals(bean.getPrefix())) {
			return bean.getPrefix()
					+ ConfigUtil.changeFirstToUpNoCheck(baseName);
		}
		return baseName;
	}
}
