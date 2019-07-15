package com.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 个税计算
 * @author anjiheng 2019年7月12日
 *
 */
public class CountTax {
	// 工资
	private static BigDecimal salary = new BigDecimal(30000);
	// 五险一金
	private static BigDecimal five_insurance = new BigDecimal(5591.11f);
	// 其他扣除
	private static BigDecimal other = new BigDecimal(0);
	
	public static void main(String args[]) {
		BigDecimal owages = salary.subtract(five_insurance).subtract(other).subtract(new BigDecimal(3500));
		BigDecimal sub = new BigDecimal("0");
		BigDecimal rate = new BigDecimal("0");
		BigDecimal b1 = new BigDecimal("36000");
		BigDecimal b2 = new BigDecimal("144000");
		BigDecimal b3 = new BigDecimal("300000");
		BigDecimal b4 = new BigDecimal("420000");
		BigDecimal b5 = new BigDecimal("660000");
		BigDecimal b6 = new BigDecimal("960000");
		BigDecimal sum = new BigDecimal("0");
		for (int i = 1; i <= 12; i++) {
			BigDecimal wages = owages.multiply(new BigDecimal(i));
			sub = new BigDecimal("0");
			rate = new BigDecimal("0");
			if (wages.compareTo(b1) < 0) {
				sub = new BigDecimal("0");
				rate = new BigDecimal("0.03");
			}
			if (wages.compareTo(b1) >= 0 && wages.compareTo(b2) < 0) {
				sub = new BigDecimal("2520");
				rate = new BigDecimal("0.10");
			}
			if (wages.compareTo(b2) >= 0 && wages.compareTo(b3) < 0) {
				sub = new BigDecimal("16920");
				rate = new BigDecimal("0.20");
			}
			if (wages.compareTo(b3) >= 0 && wages.compareTo(b4) < 0) {
				sub = new BigDecimal("31920");
				rate = new BigDecimal("0.25");
			}
			if (wages.compareTo(b4) >= 0 && wages.compareTo(b5) < 0) {
				sub = new BigDecimal("52920");
				rate = new BigDecimal("0.30");
			}
			if (wages.compareTo(b5) >= 0 && wages.compareTo(b6) < 0) {
				sub = new BigDecimal("85920");
				rate = new BigDecimal("0.35");
			}
			if (wages.compareTo(b6) >= 0) {
				sub = new BigDecimal("181920");
				rate = new BigDecimal("0.45");
			}
			BigDecimal temp = wages.multiply(rate).subtract(sub).subtract(sum);
			System.out.println(i + " 月: " + temp.setScale(2, RoundingMode.HALF_EVEN).toString() + "   -- "
					+ wages.multiply(rate).subtract(sub).setScale(2, RoundingMode.HALF_EVEN).toString());
			sum = sum.add(temp);
		}
	}
}
