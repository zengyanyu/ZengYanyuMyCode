package com.springboot.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.springboot.annotation.RequiredPermission;

/**
 * Excel控制器对象
 * @author ZengYanyu
 * @Description
 * @Date 2021年8月4日 下午9:25:32
 */
@RestController
public class ExcelController extends BaseController {

	private static final Log LOG = LogFactory.getLog(ExcelController.class);

	/**
	 * 导出excel(使用模板方式)
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年8月4日 下午9:26:00
	 * @return
	 */
	@RequiredPermission("导出excel(使用模板方式)")
	@RequestMapping({ "/exportTemplate" })
	public String exportTemplate() {
		ClassPathResource classPathResource = new ClassPathResource("template/BidEvalDtl.xls");
		try (//实现自动关闭资源
				InputStream in = classPathResource.getInputStream(); //
				OutputStream out = response.getOutputStream()) {

			HSSFWorkbook wb = new HSSFWorkbook(in);
			//设置Excel数据
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload");
			//设置中文编码
			String fileName = URLEncoder.encode("使用模板方式.xls", "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			wb.write(out);
		} catch (Exception e) {
			LOG.error("导出excel异常" + e.getMessage());
		}
		return null;
	}

	/**
	 * 不使用模板的方式
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年8月7日 上午10:34:04
	 * @return
	 */
	@RequiredPermission("不使用模板的方式")
	@RequestMapping({ "/excelExport" })
	public void excelExport() {
		//InputStream in = request.getInputStream(); 
		//实现自动关闭资源
		try (OutputStream out = response.getOutputStream()) {
			HSSFWorkbook wb = new HSSFWorkbook();
			//sheetname
			HSSFSheet createSheet = wb.createSheet("excel工作布");

			//设置标题
			String[] titleArr = new String[] { "物料编码", "物料名称", "时间" };
			HSSFRow createRowTitle = createSheet.createRow(0);

			HSSFCell titleCell = null;
			for (int i = 0; i < titleArr.length; i++) {
				titleCell = createRowTitle.createCell(i);
				titleCell.setCellValue(titleArr[i]);
			}

			//封装数据
			HSSFRow createRow = null;
			HSSFCell contentCell = null;
			//创建第一行数据
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 1; i < 20; i++) {
				createRow = createSheet.createRow(i);
				int columnIndex = 0;
				contentCell = createRow.createCell(columnIndex);
				contentCell.setCellValue("序号" + i);
				contentCell = createRow.createCell(columnIndex++);
				contentCell.setCellValue("物料编码" + i);
				contentCell = createRow.createCell(columnIndex++);
				contentCell.setCellValue("物料名称" + i);
				contentCell = createRow.createCell(columnIndex++);
				contentCell.setCellValue(dateFormat.format(Calendar.getInstance().getTime()));
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload");
			//设置中文编码
			String fileName = URLEncoder.encode("创建Excel文件方式.xls", "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			wb.write(out);
		} catch (Exception e) {
			LOG.error("导出excel异常" + e.getMessage());
		}
	}

	/**
	 * 返回的格式是XLSX
	 * @author ZengYanyu
	 * @Description
	 * @Date 2022年5月15日 下午6:07:40
	 */
	@RequiredPermission("不使用模板的方式")
	@RequestMapping({ "/excelExport1" })
	public void excelExport1() {
		//实现自动关闭资源
		try (OutputStream out = response.getOutputStream()) {
			XSSFWorkbook wb = new XSSFWorkbook();
			//sheetname
			XSSFSheet createSheet = wb.createSheet("excel工作布");

			//设置标题
			String[] titleArr = new String[] { "物料编码", "物料名称", "时间" };
			XSSFRow createRowTitle = createSheet.createRow(0);

			XSSFCell titleCell = null;
			for (int i = 0; i < titleArr.length; i++) {
				titleCell = createRowTitle.createCell(i);
				titleCell.setCellValue(titleArr[i]);
			}

			//封装数据
			XSSFRow createRow = null;
			XSSFCell contentCell = null;
			//创建第一行数据
			for (int i = 1; i < 1000; i++) {
				createRow = createSheet.createRow(i);
				int columnIndex = 0;
				contentCell = createRow.createCell(columnIndex);
				contentCell.setCellValue("序号" + i);
				contentCell = createRow.createCell(columnIndex++);
				contentCell.setCellValue("物料编码" + i);
				contentCell = createRow.createCell(columnIndex++);
				contentCell.setCellValue("物料名称" + i);
				contentCell = createRow.createCell(columnIndex++);
				contentCell.setCellValue(DATE_FORMAT.format(Calendar.getInstance().getTime()));
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload");
			//设置中文编码
			String fileName = URLEncoder.encode("创建Excel文件方式.xlsx", "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			wb.write(out);
		} catch (Exception e) {
			LOG.error("导出excel异常" + e.getMessage());
		}
	}

}
