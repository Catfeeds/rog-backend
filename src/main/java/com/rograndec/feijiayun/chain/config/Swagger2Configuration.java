package com.rograndec.feijiayun.chain.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

//import springfox.documentation.service.Contact;

@Profile({"test","dev","test_jc"})
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

	public ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("服务接口规范")
				.description("北京融贯电子商务有限公司-新版菲加云连锁-前后端接口规范")
				.termsOfServiceUrl("https://swagger.io/")
//				.contact(new Contact("zhongyi.li", "", "zhongyi.li@rograndec.com"))
				.version("2.0")
				.build();
	}

	@Bean
	public Docket store() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("store")
				.apiInfo(apiInfo())
				.select()
				.paths(pstorePath())
				.build();

	}

	@Bean
	public Docket pengding() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("pengding")
				.apiInfo(apiInfo())
				.select()
				.paths(pendingPath())
				.build();

	}

	@Bean
	public Docket warning() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("warning")
				.apiInfo(apiInfo())
				.select()
				.paths(warningPath())
				.build();

	}

		@Bean
	public Docket login() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("login")
				.apiInfo(apiInfo())
				.select()
				.paths(loginPath())
				.build();
	}
	@Bean
	public Docket register() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("register")
				.apiInfo(apiInfo())
				.select()
				.paths(registerPath())
				.build();
	}


	@Bean
	public Docket AuthMenu() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("auth_menu")
				.apiInfo(apiInfo())
				.select()
				.paths(authMenu())
				.build();
	}

	@Bean
	public Docket DistrReturnIn() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_parent_return_out")
				.apiInfo(apiInfo())
				.select()
				.paths(distrReturnIn())
				.build();
	}

	@Bean
	public Docket ReportRole() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_role")
				.apiInfo(apiInfo())
				.select()
				.paths(reportRole())
				.build();
	}

	@Bean
	public Docket ReportPosition() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_position")
				.apiInfo(apiInfo())
				.select()
				.paths(reportPosition())
				.build();
	}

	@Bean
	public Docket ReportDepartment() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_department")
				.apiInfo(apiInfo())
				.select()
				.paths(reportDept())
				.build();
	}

	@Bean
	public Docket DistrINReturnOut() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_branch_return_out")
				.apiInfo(apiInfo())
				.select()
				.paths(distrInReturnOut())
				.build();
	}

	@Bean
	public Docket DistrInStorage() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_in_storage")
				.apiInfo(apiInfo())
				.select()
				.paths(distrInstorage())
				.build();
	}

	@Bean
	public Docket UserReport() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("user_report")
				.apiInfo(apiInfo())
				.select()
				.paths(userReport())
				.build();
	}

	@Bean
	public Docket QualityReport() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report/quality/storage")
				.apiInfo(apiInfo())
				.select()
				.paths(qualityStorageReport())
				.build();
	}
	
	@Bean
	public Docket SmartCart() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("smart_cart")
				.apiInfo(apiInfo())
				.select()
				.paths(smartCart())
				.build();
	}

	@Bean
	public Docket SaleFlowReport() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("sale_flow_report")
				.apiInfo(apiInfo())
				.select()
				.paths(saleFlowReport())
				.build();
	}
	
	@Bean
	public Docket FinanceReport() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("finance_report")
				.apiInfo(apiInfo())
				.select()
				.paths(financeReport())
				.build();
	}
	
	@Bean
	public Docket SaleAnalysisReport() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("sale_analysis_report")
				.apiInfo(apiInfo())
				.select()
				.paths(saleAnalysisReport())
				.build();
	}
	
	@Bean
	public Docket ExcessSaleReport() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_retail_excessSale")
				.apiInfo(apiInfo())
				.select()
				.paths(excessSaleReport())
				.build();
	}
	
	@Bean
	public Docket ReportStorageReport() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_storage_report")
				.apiInfo(apiInfo())
				.select()
				.paths(reportStorageReport())
				.build();
	}

	@Bean
	public Docket TimeSaleReport() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_retail_timeSale")
				.apiInfo(apiInfo())
				.select()
				.paths(TimeSaleReportPath())
				.build();
	}


	@Bean
	public Docket OtherOutApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("other_out")
				.apiInfo(apiInfo())
				.select()
				.paths(ohterOut())
				.build();
	}
	@Bean
	public Docket purchaseReturnApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("purchase_return")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseReturn())
				.build();
	}

	@Bean
	public Docket RoyaltyApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("royalty")
				.apiInfo(apiInfo())
				.select()
				.paths(royalty())
				.build();
	}
	@Bean
	public Docket educationMajorApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("education_Major_Manager")
				.apiInfo(apiInfo())
				.select()
				.paths(educationMajorPath())
				.build();
	}

	@Bean
	public Docket priceMajorApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("price_manager")
				.apiInfo(apiInfo())
				.select()
				.paths(pricePath())
				.build();
	}

	@Bean
	public Docket approvalFlowManagerApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("approvalFlow_Manager")
				.apiInfo(apiInfo())
				.select()
				.paths(approvalFlowMangerPath())
				.build();
	}
	@Bean
	public Docket approvalFlowActionManagerApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("approvalFlowAction_Manager")
				.apiInfo(apiInfo())
				.select()
				.paths(approvalFlowActionMangerPath())
				.build();
	}

	@Bean
	public Docket collectMoneyApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("collectMoney")
				.apiInfo(apiInfo())
				.select()
				.paths(collectMoney())
				.build();
	}

	@Bean
	public Docket payMoneyApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("payMoney")
				.apiInfo(apiInfo())
				.select()
				.paths(payMoney())
				.build();
	}

	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rograndec.feijiayun.chain"))
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public Docket userManagerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("user_manager")
				.apiInfo(apiInfo())
				.select()
//                .paths(PathSelectors.any())
				.paths(userMangerPath())
				.build();
	}

	@Bean
	public Docket systemManagerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("system_manager")
				.apiInfo(apiInfo())
				.select()
//                .paths(PathSelectors.any())
				.paths(systemMangerPath())
				.build();
	}

	@Bean
	public Docket supplierManagerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("supplier_manager")
				.apiInfo(apiInfo())
				.select()
//                .paths(PathSelectors.any())
				.paths(supplierMangerPath())
				.build();
	}
	@Bean
	public Docket storageManagerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("warehouse_manager")
				.apiInfo(apiInfo())
				.select()
//                .paths(PathSelectors.any())
				.paths(storageMangerPath())
				.build();
	}

	@Bean
	public Docket goodsInfoManagerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("goods_info")
				.apiInfo(apiInfo())
				.select()
//                .paths(PathSelectors.any())
				.paths(goodsMangerPath())
				.build();
	}
	
	@Bean
	public Docket goodsManageManagerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("goods_manage")
				.apiInfo(apiInfo())
				.select()
//                .paths(PathSelectors.any())
				.paths(goodsManagePath())
				.build();
	}
	
	
	@Bean
	public Docket purchaseManageManagerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("in_storage")
				.apiInfo(apiInfo())
				.select()
//                .paths(PathSelectors.any())
				.paths(inStoragePath())
				.build();
	}
	
	
	@Bean
	public Docket salePaymentManageManagerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("sale_payment")
				.apiInfo(apiInfo())
				.select()
//                .paths(PathSelectors.any())
				.paths(salePaymentPath())
				.build();
	}

	
	@Bean
	public Docket goodsSetManagerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("goods_set")
				.apiInfo(apiInfo())
				.select()
//                .paths(PathSelectors.any())
				.paths(goodsSetPath())
				.build();
	}
	@Bean
	public Docket openingApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("opening_inventory")
				.apiInfo(apiInfo())
				.select()
				.paths(openingInventoryPath())
				.build();
	}
	@Bean
	public Docket purchaseCheckApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("purchase_check")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseCheckPath())
				.build();
	}

	@Bean
	public Docket retailShiftApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("retail_shift")
				.apiInfo(apiInfo())
				.select()
				.paths(retailShiftPath())
				.build();
	}

	@Bean
	public Docket ChGoodsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("chgoods")
				.apiInfo(apiInfo())
				.select()
				.paths(ChGoodsPath())
				.build();
	}


	@Bean
	public Docket purchaseReturnOutApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("purchase_return_out")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseReturnOutPath())
				.build();
	}
	
	@Bean
	public Docket purchasePlanOutApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("purchase_plan")
				.apiInfo(apiInfo())
				.select()
				.paths(purchasePlanPath())
				.build();
	}
	
	@Bean
	public Docket posManageApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("retail_pos_manage")
				.apiInfo(apiInfo())
				.select()
				.paths(posManagePath())
				.build();
	}

	@Bean
	public Docket prescriptionManageApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("retail_prescription")
				.apiInfo(apiInfo())
				.select()
				.paths(retailPrescriptionPath())
				.build();
	}
	
	@Bean
	public Docket salePricingApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("retail_sale_pricing")
				.apiInfo(apiInfo())
				.select()
				.paths(salePricingPath())
				.build();
	}
	@Bean
	public Docket purchaseOrderApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("purchase_order")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseOrderPath())
				.build();
	}
	@Bean
	public Docket goodsDestroyApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("storage_goodsHandle_goodsDestroy")
				.apiInfo(apiInfo())
				.select()
				.paths(goodsDestroyPath())
				.build();
	}
	@Bean
	public Docket lotAdjustApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("storage_lotAdjust")
				.apiInfo(apiInfo())
				.select()
				.paths(lotAdjustPath())
				.build();
	}
    @Bean
    public Docket distrReqPlanApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("distr_req_plan")
			.apiInfo(apiInfo())
			.select()
			.paths(distrReqPlanPath())
			.build();
   }
    @Bean
    public Docket distrReqPlanReportApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("report_quality_distr_distrReqPlan")
			.apiInfo(apiInfo())
			.select()
			.paths(distrReqPlanReportPath())
			.build();
   }
    @Bean
    public Docket healthCheckApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("basic_health_check")
			.apiInfo(apiInfo())
			.select()
			.paths(healthCheckPath())
			.build();
   }
    @Bean
    public Docket expireGoodsReportApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("report_quality_storage_expireGoods")
			.apiInfo(apiInfo())
			.select()
			.paths(expireGoodsReportPath())
			.build();
   }
    @Bean
    public Docket goodsDisplayCheckReportApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("report_quality_storage_goodsDisplayCheck")
			.apiInfo(apiInfo())
			.select()
			.paths(goodsDisplayCheckReportPath())
			.build();
   }
    @Bean
    public Docket goodsMaintanceReportApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("report_quality_storage_goodsMaintance")
			.apiInfo(apiInfo())
			.select()
			.paths(goodsMaintanceReportPath())
			.build();
   }
    @Bean
    public Docket lotNumberAdjustReportApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("report_quality_storage_lotNumberAdjust")
			.apiInfo(apiInfo())
			.select()
			.paths(lotNumberAdjustReportPath())
			.build();
   }
    @Bean
    public Docket temperatureHumidityReportApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("report_quality_storage_temperatureHumidity")
			.apiInfo(apiInfo())
			.select()
			.paths(temperatureHumidityReportPath())
			.build();
   }
    @Bean
    public Docket unqualifiedGoodsReportApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("report_quality_storage_unqualifiedGoods")
			.apiInfo(apiInfo())
			.select()
			.paths(unqualifiedGoodsReportPath())
			.build();
   }
    @Bean
    public Docket MedicineConsultApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("medicine_consult")
			.apiInfo(apiInfo())
			.select()
			.paths(medicineConsultPath())
			.build();
   }
    @Bean
    public Docket distrInReturnApi() {
	   return new Docket(DocumentationType.SWAGGER_2)
			.groupName("distr_in_return")
			.apiInfo(apiInfo())
			.select()
			.paths(distrInReturnPath())
			.build();
   }
	@Bean
	public Docket memberSetApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("member_set")
				.apiInfo(apiInfo())
				.select()
				.paths(memberSetPath())
				.build();
	}
	
	@Bean
	public Docket infPosSetApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("inf_pos")
				.apiInfo(apiInfo())
				.select()
				.paths(infPosSetPath())
				.build();
	}
	


	@Bean
	public Docket memberInfoApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("member_info")
				.apiInfo(apiInfo())
				.select()
				.paths(memberInfoPath())
				.build();
	}

	@Bean
	public Docket moveShelfApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("shelf_move")
				.apiInfo(apiInfo())
				.select()
				.paths(moveShelfPath())
				.build();
	}

	@Bean
	public Docket SpecialRegisterApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("pecial_register")
				.apiInfo(apiInfo())
				.select()
				.paths(SpecialRegisterPath())
				.build();
	}
	@Bean
	public Docket GoodsLockApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("goods_lock")
				.apiInfo(apiInfo())
				.select()
				.paths(GoodsLockPath())
				.build();
	}
	@Bean
	public Docket GoodsHandleApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("goods_handle")
				.apiInfo(apiInfo())
				.select()
				.paths(GoodsHandlePath())
				.build();
	}
	@Bean
	public Docket EquipmentApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("equipment")
				.apiInfo(apiInfo())
				.select()
				.paths(EquipmentPath())
				.build();
	}
    @Bean
    public Docket GoodsLockReportApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("report_quality_storage_goods")
                .apiInfo(apiInfo())
                .select()
                .paths(GoodsSeriesReportPath())
                .build();
    }
	@Bean
	public Docket GoodsShelfMoveReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_storage")
				.apiInfo(apiInfo())
				.select()
				.paths(GoodsShelfMoveReportPath())
				.build();
	}
	@Bean
	public Docket EquipmentReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_equipment_equipmentStandAccount")
				.apiInfo(apiInfo())
				.select()
				.paths(EquipmentReportPath())
				.build();
	}
	@Bean
	public Docket EquipmentMaintanceReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_equipment_equipmentMaintance")
				.apiInfo(apiInfo())
				.select()
				.paths(EquipmentMaintanceReportPath())
				.build();
	}
	@Bean
	public Docket EquipmentTypeApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("equipment_type")
				.apiInfo(apiInfo())
				.select()
				.paths(EquipmentTypePath())
				.build();
	}
	@Bean
	public Docket EquipmentCheckApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("equipment_check")
				.apiInfo(apiInfo())
				.select()
				.paths(EquipmentCheckPath())
				.build();
	}
	@Bean
	public Docket EquipmentCheckReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_equipment_equipmentCheck")
				.apiInfo(apiInfo())
				.select()
				.paths(EquipmentCheckReportPath())
				.build();
	}
	@Bean
	public Docket EquipmentVerifyApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("equipment_verify")
				.apiInfo(apiInfo())
				.select()
				.paths(EquipmentVerifyPath())
				.build();
	}
	@Bean
	public Docket EquipmentVerifyReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_equipment_equipmentVerify")
				.apiInfo(apiInfo())
				.select()
				.paths(EquipmentVerifyReportPath())
				.build();
	}

    @Bean
    public Docket DistrSendApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("distr_send")
                .apiInfo(apiInfo())
                .select()
                .paths(DistrSendPath())
                .build();
    }
    
    @Bean
    public Docket DistrPickApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("distr_pick")
                .apiInfo(apiInfo())
                .select()
                .paths(DistrPickPath())
                .build();
    }
    
    @Bean
    public Docket DistrSendReportApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("report_quality_distr_distrSend")
                .apiInfo(apiInfo())
                .select()
                .paths(DistrSendReportPath())
                .build();
    }

    @Bean
    public Docket AftersaleRecoverApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("aftersale_recover")
                .apiInfo(apiInfo())
                .select()
                .paths(AftersaleRecoverPath())
                .build();
    }

	@Bean
	public Docket DistrOutApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_out")
				.apiInfo(apiInfo())
				.select()
				.paths(DistrOutPath())
				.build();
	}
	@Bean
	public Docket DistrOutReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_distr_distrOut")
				.apiInfo(apiInfo())
				.select()
				.paths(DistrOutReportPath())
				.build();
	}
	@Bean
	public Docket DistrLackApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_lack")
				.apiInfo(apiInfo())
				.select()
				.paths(DistrLackPath())
				.build();
	}
	@Bean
	public Docket DistrLackReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_distr_distrLack")
				.apiInfo(apiInfo())
				.select()
				.paths(DistrLackReportPath())
				.build();
	}
	@Bean
	public Docket commonControllerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("common_controller")
				.apiInfo(apiInfo())
				.select()
				.paths(commonControllerPath())
				.build();
	}

	@Bean
	public Docket inventoryOrderControllerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("storage_inventory_inventoryOrder")
				.apiInfo(apiInfo())
				.select()
				.paths(inventoryOrderControllerPath())
				.build();
	}

	@Bean
	public Docket inventoryRegisterControllerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("storage_inventory_inventoryRegister")
				.apiInfo(apiInfo())
				.select()
				.paths(inventoryRegisterControllerPath())
				.build();
	}

	@Bean
	public Docket inventoryDiffControllerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("storage_inventory_inventoryDiffHandle")
				.apiInfo(apiInfo())
				.select()
				.paths(inventoryDiffControllerPath())
				.build();
	}

	@Bean
	public Docket inventoryPostControllerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("storage_inventory_inventoryPost")
				.apiInfo(apiInfo())
				.select()
				.paths(inventoryPostControllerPath())
				.build();
	}

	@Bean
	public Docket distrReturnCheckControllerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_return_check")
				.apiInfo(apiInfo())
				.select()
				.paths(distrReturnCheckControllerPath())
				.build();
	}

	@Bean
	public Docket recallControllerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("aftersale_recall")
				.apiInfo(apiInfo())
				.select()
				.paths(recallControllerPath())
				.build();
	}




	@Bean
	public Docket DistrInNoticeApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_in_notice")
				.apiInfo(apiInfo())
				.select()
				.paths(DistrInNoticePath())
				.build();
	}
	@Bean
	public Docket DistrInNoticeReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_distr_distrInNotice")
				.apiInfo(apiInfo())
				.select()
				.paths(DistrInNoticeReportPath())
				.build();
	}

	@Bean
	public Docket DistrReturnNoticeApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_return_notice")
				.apiInfo(apiInfo())
				.select()
				.paths(DistrReturnNoticePath())
				.build();
	}

	@Bean
	public Docket distrInCheckApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_in_check")
				.apiInfo(apiInfo())
				.select()
				.paths(distrInCheckPath())
				.build();
	}

	@Bean
	public Docket TrainPlanApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("train_plan")
				.apiInfo(apiInfo())
				.select()
				.paths(TrainPlanPath())
				.build();
	}
	
	@Bean
	public Docket reportMemberApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_member")
				.apiInfo(apiInfo())
				.select()
				.paths(reportMemberPath())
				.build();
	}
	

	private Predicate<String> reportMemberPath() {
		return or(
				regex("/report/member.*"));
	}

	@Bean
	public Docket reportQuanlityUserTrainPlan() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_user")
				.apiInfo(apiInfo())
				.select()
				.paths(reportQuanlityUserTrainPlanPath())
				.build();
	}

	@Bean
	public Docket ComplainApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("complain")
				.apiInfo(apiInfo())
				.select()
				.paths(ComplainPath())
				.build();
	}

	@Bean
	public Docket IncompatibilityApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("incompatibility-pharmacySet-specialGoods")
				.apiInfo(apiInfo())
				.select()
				.paths(IncompatibilityPath())
				.build();
	}

	@Bean
	public Docket reportOrgApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_org_org")
				.apiInfo(apiInfo())
				.select()
				.paths(reportOrgPath())
				.build();
	}

	@Bean
	public Docket reportQualityOrgApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_org_orgQualification")
				.apiInfo(apiInfo())
				.select()
				.paths(reportQualityOrgPath())
				.build();
	}

	@Bean
	public Docket reportSupplierQualityOrgApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_supplierQualification")
				.apiInfo(apiInfo())
				.select()
				.paths(reportSupplierQualityPath())
				.build();
	}

	@Bean
	public Docket qualityManageSystemApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("quality_manage_system")
				.apiInfo(apiInfo())
				.select()
				.paths(qualityManageSystemPath())
				.build();
	}

	@Bean
	public Docket qualityManageSystemFileApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("quality_manage_system_file")
				.apiInfo(apiInfo())
				.select()
				.paths(qualityManageSystemFilePath())
				.build();
	}

	@Bean
	public Docket purchaseCheckReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_purchaseCheck")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseCheckReportPath())
				.build();
	}

	@Bean
	public Docket purchaseReturnReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_purchaseReturn")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseReturnReportPath())
				.build();
	}

	@Bean
	public Docket purchaseReturnOutReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_purchaseReturnOut")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseReturnOutReportPath())
				.build();
	}

	@Bean
	public Docket purchaseInStorageReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_purchaseInStorage")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseInStorageReportPath())
				.build();
	}


	@Bean
	public Docket distrInReturnOutReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_distr_distrInReturnOut")
				.apiInfo(apiInfo())
				.select()
				.paths(distrInReturnOutReportPath())
				.build();
	}

	@Bean
	public Docket distrInReturnReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_distr_distrInReturn")
				.apiInfo(apiInfo())
				.select()
				.paths(distrInReturnReportPath())
				.build();
	}

	@Bean
	public Docket distrInStorageReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_distr_distrInStorage")
				.apiInfo(apiInfo())
				.select()
				.paths(distrInStorageReportPath())
				.build();
	}

	@Bean
	public Docket distrInCheckQuaReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_distr_distrInCheckQua")
				.apiInfo(apiInfo())
				.select()
				.paths(distrInCheckQuaReportPath())
				.build();
	}

	@Bean
	public Docket purchaseInCheckQuaReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_purchaseCheckQua")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseInCheckQuaReportPath())
				.build();
	}

	@Bean
	public Docket purchaseReviewCheckQuaReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_purchaseReviewCheck")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseReviewCheckQuaReportPath())
				.build();
	}

	@Bean
	public Docket goodsDtlAccountReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_storage_goodsDtlAccount")
				.apiInfo(apiInfo())
				.select()
				.paths(goodsDtlAccountReportPath())
				.build();
	}

	@Bean
	public Docket achieveActivityEntrepriseApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("achieve_activity_entreprise")
				.apiInfo(apiInfo())
				.select()
				.paths(achieveActivityEntreprisePath())
				.build();
	}

	@Bean
	public Docket onlineMarketApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("online_market")
				.apiInfo(apiInfo())
				.select()
				.paths(onlineMarketPath())
				.build();
	}

	private Predicate<String> onlineMarketPath() {
		return or(
				regex("/online/purchase/smart/onlineMarket.*"));
	}

	private Predicate<String> achieveActivityEntreprisePath() {
		return or(
				regex("/online/purchase/centralized/achieveActivityEntreprise.*"));
	}

	@Bean
	public Docket CentralizedCartApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("online_pruchase_centralized_cart")
				.apiInfo(apiInfo())
				.select()
				.paths(CentralizedCartPath())
				.build();
	}
	
	@Bean
	public Docket AccountingPeriodApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("accounting_period")
				.apiInfo(apiInfo())
				.select()
				.paths(AccountingPeriodPath())
				.build();
	}
	
	@Bean
	public Docket FinalSettleApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("accounting_finalsettle")
				.apiInfo(apiInfo())
				.select()
				.paths(FinalSettlePath())
				.build();
	}

	@Bean
	public Docket ReveivableinvoiceApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("receivable_invoice")
				.apiInfo(apiInfo())
				.select()
				.paths(ReveivableinvoicePath())
				.build();
	}

	@Bean
	public Docket PrepayInvoiceApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("finance_prepay_invoice")
				.apiInfo(apiInfo())
				.select()
				.paths(PrepayInvoicePath())
				.build();
	}

	@Bean
	public Docket PaymentInvoiceApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("finance_payment_invoice")
				.apiInfo(apiInfo())
				.select()
				.paths(PaymentInvoicePath())
				.build();
	}

	@Bean
	public Docket VerificationFormApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("finance_verification_form")
				.apiInfo(apiInfo())
				.select()
				.paths(VerificationFormPath())
				.build();
	}

	@Bean
	public Docket CommissionApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("sale_commission")
				.apiInfo(apiInfo())
				.select()
				.paths(CommissionPath())
				.build();
	}

	private Predicate<String> PrepayInvoicePath() {
		return or(
				regex("/prepay/invoice.*"));
	}

	private Predicate<String> PaymentInvoicePath() {
		return or(
				regex("/payment/invoice.*"));
	}

	private Predicate<String> VerificationFormPath() {
		return or(
				regex("/verification/form/.*"));
	}

	private Predicate<String> CommissionPath() {
		return or(
				regex("/commission.*"));
	}

	private Predicate<String> ReveivableinvoicePath() {
		return or(
				regex("/finance/receivableinvoice.*"));
	}

	private Predicate<String> FinalSettlePath() {
		return or(
				regex("/finance/finalsettle.*"));
	}

	private Predicate<String> AccountingPeriodPath() {
		return or(
				regex("/finance/set/period.*"));
	}

	private Predicate<String> CentralizedCartPath() {
		return or(
				regex("/online/pruchase/centralized/cart.*"));
	}

	private Predicate<String> purchaseInStorageReportPath() {
		return or(
				regex("/report/quality/purchase/purchaseInStorage.*"));
	}

	private Predicate<String> distrInReturnOutReportPath() {
		return or(
				regex("/report/quality/distr/distrInReturnOut.*"));
	}

	private Predicate<String> distrInReturnReportPath() {
		return or(
				regex("/report/quality/distr/distrInReturn.*"));
	}

	private Predicate<String> distrInStorageReportPath() {
		return or(
				regex("/report/quality/distr/distrInStorage.*"));
	}

	private Predicate<String> distrInCheckQuaReportPath() {
		return or(
				regex("/report/quality/distr/distrInCheckQua.*"));
	}

	private Predicate<String> purchaseInCheckQuaReportPath() {
		return or(
				regex("/report/quality/purchase/purchaseCheckQua.*"));
	}

	private Predicate<String> purchaseReviewCheckQuaReportPath() {
		return or(
				regex("/report/quality/purchase/purchaseReviewCheck.*"));
	}

	private Predicate<String> goodsDtlAccountReportPath() {
		return or(
				regex("/report/storage/goodsDtlAccount.*"));
	}

	private Predicate<String> purchaseCheckReportPath() {
		return or(
				regex("/report/quality/purchase/purchaseCheck.*"));
	}
	private Predicate<String> purchaseReturnReportPath() {
		return or(
				regex("/report/quality/purchase/purchaseReturn.*"));
	}

	private Predicate<String> purchaseReturnOutReportPath() {
		return or(
				regex("/report/quality/purchase/purchaseReturnOut.*"));
	}
	private Predicate<String> qualityManageSystemFilePath() {
		return or(
				regex("/quality/file/qualityManageSystemFile.*"));
	}

	private Predicate<String> qualityManageSystemPath() {
		return or(
				regex("/quality/system/qualityManageSystem.*"));
	}
	
	/**
	 * 
	 * @Description: 访问标准库接口
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月18日 上午10:48:54 
	 * @return 
	 * @return Docket
	 */
	@Bean
	public Docket goodsStandardApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("goods_standard")
				.apiInfo(apiInfo())
				.select()
				.paths(goodsStandardPath())
				.build();
	}

	private Predicate<String> goodsStandardPath() {
		return or(
				regex("/goods/standard.*"));
	}


	@Bean
	public Docket reportWarnOrgApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_org_orgQualificationWarn")
				.apiInfo(apiInfo())
				.select()
				.paths(reportWarnOrgPath())
				.build();
	}

	@Bean
	public Docket reportSupplierWarnApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_supplierQualificationWarn")
				.apiInfo(apiInfo())
				.select()
				.paths(reportSupplierWarnPath())
				.build();
	}

	@Bean
	public Docket reportSupplierSalerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_supplierSaler")
				.apiInfo(apiInfo())
				.select()
				.paths(reportSupplierSalerPath())
				.build();
	}

	@Bean
	public Docket reportSupplierVariesApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_supplierVarieties")
				.apiInfo(apiInfo())
				.select()
				.paths(reportSupplierVarietiPath())
				.build();
	}
	@Bean
	public Docket reportSubOrgQualificationApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_sub_org_orgQualification")
				.apiInfo(apiInfo())
				.select()
				.paths(reportSubOrgQualificationPath())
				.build();
	}
	@Bean
	public Docket reportSupplierFileApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_supplierFile")
				.apiInfo(apiInfo())
				.select()
				.paths(reportSupplierFilePath())
				.build();
	}
	@Bean
	public Docket reportNearEffectPeriodGoodsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_storage_nearEffectPeriodGoods")
				.apiInfo(apiInfo())
				.select()
				.paths(reportNearEffectPeriodGoodsPath())
				.build();
	}
	
	@Bean
	public Docket reportQualityLagSaleApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_storage_lagSale")
				.apiInfo(apiInfo())
				.select()
				.paths(reportQualityLagSalePath())
				.build();
	}
	
	@Bean
	public Docket reportQualityRetailSaleApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_retail_sale")
				.apiInfo(apiInfo())
				.select()
				.paths(reportQualityRetailSalePath())
				.build();
	}

	@Bean
	public Docket purchaseReceiveApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("purchase_receive")
				.apiInfo(apiInfo())
				.select()
				.paths(purchaseReceivePath())
				.build();
	}

	@Bean
	public Docket reportPurchaseReceiveApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_purchaseReceive")
				.apiInfo(apiInfo())
				.select()
				.paths(reportPurchaseReceivePath())
				.build();
	}

	@Bean
	public Docket reportQualityPurchaseGoodsLicenseWarnApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_goodsLicenseWarn")
				.apiInfo(apiInfo())
				.select()
				.paths(reportQualityPurchaseGoodsLicenseWarnPath())
				.build();
	}

	@Bean
	public Docket reportQualityPurchaseGoodsLicenseApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_goodsLicense")
				.apiInfo(apiInfo())
				.select()
				.paths(reportQualityPurchaseGoodsLicensePath())
				.build();
	}

	@Bean
	public Docket reportQualityPurchasePurchaseOrderApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_purchaseOrder")
				.apiInfo(apiInfo())
				.select()
				.paths(reportQualityPurchasePurchaseOrderPath())
				.build();
	}

	@Bean
	public Docket reportQualityPurchaseFirstGoodsReviewApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_firstGoodsReview")
				.apiInfo(apiInfo())
				.select()
				.paths(reportQualityPurchaseFirstGoodsReviewPath())
				.build();
	}

	@Bean
	public Docket reportQualityPurchasePurchasePlanApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_purchasePlan")
				.apiInfo(apiInfo())
				.select()
				.paths(reportQualityPurchasePurchasePlanPath())
				.build();
	}
	
	@Bean
	public Docket reportFinanceAccountAdjustApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_finance_account_adjust")
				.apiInfo(apiInfo())
				.select()
				.paths(reportFinanceAccountAdjustPath())
				.build();
	}

	private Predicate<String> reportFinanceAccountAdjustPath() {
		return or(
				regex("/report/finance/account/adjust*.*"));
	}

	private Predicate<String> reportQualityPurchasePurchasePlanPath() {
		return or(
				regex("/report/quality/purchase/purchasePlan*.*"));
	}

	private Predicate<String> reportQualityPurchasePurchaseOrderPath() {
		return or(
				regex("/report/quality/purchase/purchaseOrder*.*"));
	}

	private Predicate<String> reportQualityPurchaseFirstGoodsReviewPath() {
		return or(
				regex("/report/quality/purchase/firstGoodsReview*.*"));
	}

	private Predicate<String> reportQualityPurchaseGoodsLicensePath() {
		return or(
				regex("/report/quality/purchase/goodsLicense*.*"));
	}



	private Predicate<String> reportQualityPurchaseGoodsLicenseWarnPath() {
		return or(
				regex("/report/quality/purchase/goodsLicenseWarn*.*"));
	}

	private Predicate<String> reportPurchaseReceivePath() {
		return or(
				regex("/report/quality/purchase/purchaseReceive*.*"));
	}

	private Predicate<String> purchaseReceivePath() {
		return or(
				regex("/purchase/receive*.*"));
	}
	
	private Predicate<String> reportQualityRetailSalePath() {
		return or(
				regex("/report/quality/retail/.*"));
	}

	private Predicate<String> reportQualityLagSalePath() {
		return or(
				regex("/report/quality/storage/lagSale.*"));
	}


	private Predicate<String> reportNearEffectPeriodGoodsPath() {
		return or(
				regex("/report/quality/storage/nearEffectPeriodGoods.*"));
	}

	private Predicate<String> loginPath() {
		return or(
				regex("/login/.*"));
	}
	private Predicate<String> warningPath() {
		return or(
				regex("/index/warning/.*"));
	}

	private Predicate<String> pendingPath() {
		return or(
				regex("/index/pending/.*"));
	}

	private Predicate<String> pstorePath() {
		return or(
				regex("/store/.*"));
	}
	private Predicate<String> registerPath() {
		return or(
				regex("/register/.*"));
	}


	private Predicate<String> IncompatibilityPath() {
		return or(
				regex("/goods/pharmacy.*"));
	}

	private Predicate<String> ComplainPath() {
		return or(
				regex("/complain.*"));
	}



	private Predicate<String> reportQuanlityUserTrainPlanPath() {
		return or(
				regex("/report/quality/user.*"));
	}

	private Predicate<String> TrainPlanPath() {
		return or(
				regex("/train/plan.*"));
	}

	private Predicate<String> DistrInNoticePath() {
		return or(
				regex("/distr/in/notice.*"));
	}

	private Predicate<String> DistrInNoticeReportPath() {
		return or(
				regex("/report/quality/distr/distrInNotice.*"));
	}

	private Predicate<String> DistrReturnNoticePath(){
		return or(
				regex("/distr/return/notice.*"));
	}
	private Predicate<String> distrInCheckPath(){
		return or(
				regex("/distr/in/check.*"));
	}
	private Predicate<String> ChGoodsPath() {
		return or(
				regex("/storage/chgoods.*"));
	}

	private Predicate<String> memberInfoPath() {
		return or(
				regex("/member.*"));
	}

	private Predicate<String> moveShelfPath() {
		return or(
				regex("/storage/move.*"));
	}

	private Predicate<String> GoodsLockPath() {
		return or(
				regex("/goodsHandl/goodsLockgoodslock.*"));
	}
	private Predicate<String> SpecialRegisterPath() {
		return or(
				regex("/retail/specialregister.*"));
	}
	private Predicate<String> GoodsHandlePath() {
		return or(
				regex("/goodsHandl/goodsLockgoodshandle.*"));
	}
	private Predicate<String> EquipmentPath() {
		return or(
				regex("/basic/equipment.*"));
	}
    private Predicate<String> GoodsSeriesReportPath() {
        return or(
                regex("/report/quality/storage/goods*.*"),
                regex("/report/quality/storage/stopSaleNotice.*"),
                regex("/report/quality/storage/startSaleNotice.*"),
                regex("/report/quality/storage/inventoryRecord.*"));
    }
	private Predicate<String>  GoodsShelfMoveReportPath() {
		return or(
				regex("/report/quality/storage.*"));
	}
	private Predicate<String> EquipmentReportPath() {
		return or(
				regex("/report/quality/equipment/equipmentStandAccount.*"));
	}
	private Predicate<String> EquipmentMaintanceReportPath() {
		return or(
				regex("/report/quality/equipment/equipmentMaintance.*"));
	}
	private Predicate<String> EquipmentTypePath() {
		return or(
				regex("/basic/equipmentType.*"));
	}
	private Predicate<String> EquipmentCheckPath() {
		return or(
				regex("/basic/equipmentCheck.*"));
	}
	private Predicate<String> EquipmentCheckReportPath() {
		return or(
				regex("/report/quality/equipment/equipmentCheck.*"));
	}
	private Predicate<String> EquipmentVerifyPath() {
		return or(
				regex("/basic/equipmentVerify.*"));
	}
	private Predicate<String> EquipmentVerifyReportPath() {
		return or(
				regex("/report/quality/equipment/equipmentVerify.*"));
	}
    private Predicate<String> DistrSendPath() {
        return or(
                regex("/distr/send.*"));
    }
    private Predicate<String> DistrPickPath() {
        return or(
                regex("/distr/pick.*"));
    }
    private Predicate<String> DistrSendReportPath() {
        return or(
                regex("/report/quality/distr/distrSend.*"));
    }
    private Predicate<String> AftersaleRecoverPath() {
        return or(
                regex("/aftersale/recover.*"));
    }
	private Predicate<String> DistrOutPath() {
		return or(
				regex("/distr/distrOut.*"));
	}
	private Predicate<String> DistrOutReportPath() {
		return or(
				regex("/report/quality/distr/distrOut.*"));
	}
	private Predicate<String> DistrLackPath() {
		return or(
				regex("/distr/distrLack.*"));
	}
	private Predicate<String> DistrLackReportPath() {
		return or(
				regex("/report/quality/distr/distrLack.*"));
	}
	private Predicate<String> commonControllerPath() {
		return or(
				regex("/common/controller.*"));
	}

	private Predicate<String> inventoryOrderControllerPath() {
		return or(
				regex("/storage/inventory/inventoryOrder.*"));
	}
	private Predicate<String> inventoryRegisterControllerPath() {
		return or(
				regex("/storage/inventory/inventoryRegister.*"));
	}
	private Predicate<String> inventoryDiffControllerPath() {
		return or(
				regex("/storage/inventory/inventoryDiffHandle.*"));
	}

	private Predicate<String> inventoryPostControllerPath() {
		return or(
				regex("/storage/inventory/inventoryPost.*"));
	}

	private Predicate<String> retailShiftPath() {
		return or(
				regex("/retail/shift.*"));
	}
	private Predicate<String> purchaseCheckPath() {
		return or(
				regex("/purchase/check.*"));
	}
	private Predicate<String> pricePath() {
		return or(
				regex("/price/*.*"));
	}
	private Predicate<String> purchaseOrderPath() {
		return or(
				regex("/purchase/order/*.*"));
	}
	private Predicate<String> goodsDestroyPath() {
		return or(
				regex("/storage/goodsHandle/goodsDestroy*.*"));
	}
	private Predicate<String> lotAdjustPath() {
		return or(
				regex("/storage/lotAdjust*.*"));
	}
	private Predicate<String> distrReqPlanPath() {
		return or(
				regex("/distr/req/plan*.*"));
	}
	private Predicate<String> distrReqPlanReportPath() {
		return or(
				regex("/report/quality/distr/distrReqPlan*.*"));
	}
	private Predicate<String> healthCheckPath() {
		return or(
				regex("/basic/health/check*.*"));
	}
	private Predicate<String> expireGoodsReportPath() {
		return or(
				regex("/report/quality/storage/expireGoods*.*"));
	}
	private Predicate<String> goodsDisplayCheckReportPath() {
		return or(
				regex("/report/quality/storage/goodsDisplayCheck*.*"));
	}
	private Predicate<String> goodsMaintanceReportPath() {
		return or(
				regex("/report/quality/storage/goodsMaintance*.*"));
	}
	private Predicate<String> lotNumberAdjustReportPath() {
		return or(
				regex("/report/quality/storage/lotNumberAdjust*.*"));
	}
	private Predicate<String> temperatureHumidityReportPath() {
		return or(
				regex("/report/quality/storage/temperatureHumidity*.*"));
	}
	private Predicate<String> unqualifiedGoodsReportPath() {
		return or(
				regex("/report/quality/storage/unqualifiedGoods*.*"));
	}
	private Predicate<String> medicineConsultPath() {
		return or(
				regex("/medicine/consult*.*"));
	}
	
	private Predicate<String> distrInReturnPath() {
		return or(
				regex("/distr/in/return/.*"));
	}
	private Predicate<String> educationMajorPath() {
		return or(
				regex("/basic/educationmajor.*"));
	}
	private Predicate<String> purchaseReturn() {
		return or(
				regex("/purchase/return/.*"));
	}
	private Predicate<String> ohterOut() {
		return or(
				regex("/storage/move/otherOut/.*"));
	}

	private Predicate<String> distrReturnIn() {
		return or(
				regex("/distr/return/in/.*"));
	}


	private Predicate<String> authMenu() {
			return or(
					regex("/auth/menu/.*"));
	}

	private Predicate<String> reportDept() {
		return or(
				regex("/report/quality/org/department/.*"));
	}

	private Predicate<String> reportPosition() {
		return or(
				regex("/report/quality/org/position/.*"));
	}

	private Predicate<String> reportRole() {
			return or(
					regex("/report/quality/org/role/.*"));
	}

	private Predicate<String> distrInReturnOut() {
		return or(
				regex("/distr/in/return/out.*"));
	}
	private Predicate<String> royalty() {
		return or(
				regex("/royalty/.*"));
	}

	private Predicate<String> userReport() {
		return or(
				regex("/report/quality/user/user.*"));
	}

	private Predicate<String> qualityStorageReport() {
		return or(
				regex("/report/quality/storage.*"));
	}

	private Predicate<String> smartCart(){
		return or(
				regex("/smart/plan.*"));
	}
	
	private Predicate<String> saleFlowReport() {
		return or(
				regex("/report/retail/saleFlow.*"));
	}
	
	private Predicate<String> saleAnalysisReport() {
		return or(
				regex("/report/retail/saleAnalysis.*"));
	}
	
	private Predicate<String> excessSaleReport() {
		return or(
				regex("/report/retail/excessSale.*"));
	}
	
	private Predicate<String> reportStorageReport() {
		return or(
				regex("/report/storage.*"));
	}
	
	private Predicate<String> TimeSaleReportPath() {
		return or(
				regex("/report/retail/timeSale.*"));
	}

	private Predicate<String> approvalFlowMangerPath() {
		return or(
				regex("/approvalFlow.*"));
	}
	private Predicate<String> approvalFlowActionMangerPath() {
		return or(
				regex("/approvalFlowAction.*"));
	}
	private Predicate<String> collectMoney() {
		return or(
				regex("/finance/collectMoney.*"));
	}

	private Predicate<String> payMoney() {
		return or(
				regex("/finance/payMoney.*"));
	}
	private Predicate<String> userMangerPath() {
		return or(
				regex("/basic/user.*"));
	}
	private Predicate<String> systemMangerPath() {
		return or(
				regex("/system.*"));
	}
	private Predicate<String> supplierMangerPath() {
		return or(
				regex("/basic/supplier.*"));
	}
	private Predicate<String> storageMangerPath() {
		return or(
				regex("/basic/warehouse.*"));
	}

	private Predicate<String> goodsMangerPath() {
		return or(
				regex("/goods/info.*"));
	}
	
	private Predicate<String> goodsManagePath() {
		return or(
				regex("/goodsManage.*"));
	}
	
	private Predicate<String> inStoragePath() {
		return or(
				regex("/instorage.*"));
	}
	
	private Predicate<String> salePaymentPath() {
		return or(
				regex("/salePayment.*"));
	}
	
	private Predicate<String> goodsSetPath() {
		return or(
				regex("/goods/set.*"));
	}

	private Predicate<String> openingInventoryPath() {
		return or(
				regex("/system/opening.*"));
	}
	private Predicate<String> purchaseReturnOutPath() {
		return or(
				regex("/purchase/returnOut.*"));
	}
	
	private Predicate<String> posManagePath() {
		return or(
				regex("/retail/pos.*"));
	}

	private Predicate<String> retailPrescriptionPath() {
		return or(
				regex("/retail/prescription.*"));
	}
	
	private Predicate<String> salePricingPath() {
		return or(
				regex("/retail/salePricing.*"));
	}
	
	private Predicate<String> infPosSetPath() {
		return or(
				regex("/inf/pos.*"));
	}

	private Predicate<String> purchasePlanPath() {
		return or(
				regex("/purchase/plan.*"));
	}
	private Predicate<String> memberSetPath() {
		return or(
				regex("/member/set.*"));
	}
	private Predicate<String> distrReturnReceivePath() {
		return or(
				regex("/distr/return/receive.*"));
	}
	private Predicate<String> distrInReceivePath() {
		return or(
				regex("/distr/in/receive.*"));
	}
	private Predicate<String> distrInReceiveReportPath() {
		return or(
				regex("/report/quality/distr/distrInReceive.*"));
	}
	@Bean
	public Docket DistrReturnReceiveApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_return_receive")
				.apiInfo(apiInfo())
				.select()
				.paths(distrReturnReceivePath())
				.build();
	}
	@Bean
	public Docket DistrInReceiveApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("distr_in_receive")
				.apiInfo(apiInfo())
				.select()
				.paths(distrInReceivePath())
				.build();
	}
	@Bean
	public Docket DistrInReceiveReportApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_distr_distrInReceive")
				.apiInfo(apiInfo())
				.select()
				.paths(distrInReceiveReportPath())
				.build();
	}


	@Bean
	public Docket storageApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("storage")
				.apiInfo(apiInfo())
				.select()
				.paths(stroagePath())
				.build();
	}

	@Bean
	public Docket fileServiceApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("file")
				.apiInfo(apiInfo())
				.select()
				.paths(fileServicePath())
				.build();
	}
	@Bean
	public Docket report_quality_purchase_supplierFistApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_purchase_supplierFist")
				.apiInfo(apiInfo())
				.select()
				.paths(supplierFirstPath())
				.build();
	}
	@Bean
	public Docket DistrQualityApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_quality_distr")
				.apiInfo(apiInfo())
				.select()
				.paths(DistrQualityPath())
				.build();
	}

	private Predicate<String> DistrQualityPath() {
		return or(
				regex("/report/quality/distr/*.*"));
	}

	private Predicate<String> stroagePath() {
		return or(
				regex("/storage/*.*"));
	}
	private Predicate<String> fileServicePath() {
		return or(
				regex("/file.*"));
	}
	private Predicate<String> supplierFirstPath() {
		return or(
				regex("/report/quality/purchase/supplierFirst.*"));
	}
	private Predicate<String> reportOrgPath() {
		return or(
				regex("/report/quality/org/org.*"));
	}
	private Predicate<String> reportQualityOrgPath() {
		return or(
				regex("/report/quality/org/orgQualification.*"));
	}

	private Predicate<String> reportSupplierQualityPath() {
		return or(
				regex("/report/quality/purchase/supplierQualification.*"));
	}

	private Predicate<String> reportWarnOrgPath() {
		return or(
				regex("/report/quality/org/orgQualificationWarn.*"));
	}
	private Predicate<String> reportSupplierWarnPath() {
		return or(
				regex("/report/quality/purchase/supplierQualificationWarn.*"));
	}

	private Predicate<String> reportSupplierSalerPath() {
		return or(
				regex("/report/quality/purchase/supplierSaler.*"));
	}
	private Predicate<String> reportSupplierVarietiPath() {
		return or(
				regex("/report/quality/purchase/supplierVarieties.*"));
	}
	private Predicate<String> reportSubOrgQualificationPath() {
		return or(
				regex("/report/quality/sub/org/orgQualification.*"));
	}
	private Predicate<String> reportSupplierFilePath() {
		return or(
				regex("/report/quality/purchase/supplierFile.*"));
	}
	@Bean
	public Docket AfterSaleAdverseApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("aftersale-adverse")
				.apiInfo(apiInfo())
				.select()
				.paths(adversePath())
				.build();
	}

	private Predicate<String> adversePath() {
		return or(
				regex("/aftersale/adverse/*.*"));
	}

	private Predicate<String> recallControllerPath() {
		return or(
				regex("/aftersale/recall.*"));
	}
	private Predicate<String> distrReturnCheckControllerPath() {
		return or(
				regex("/distr/return/check.*"));
	}

	private Predicate<String> distrInstorage() {
		return or(
				regex("/distr/in/storage.*"));
	}
	
	private Predicate<String> financeReport() {
		return or(
				regex("/report/finance.*"));
	}

	@Bean
	public Docket ReportAfterSale() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_aftersale")
				.apiInfo(apiInfo())
				.select()
				.paths(reportAfterSale())
				.build();
	}

	private Predicate<String> reportAfterSale() {
		return or(
				regex("/report/quality/aftersale/*.*"));
	}

	@Bean
	public Docket QualityReview(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("quality-review")
				.apiInfo(apiInfo())
				.select()
				.paths(qualityReview())
				.build();
	}

	private Predicate<String> qualityReview() {
		return or(
				regex("/quality/review/*.*"));
	}

	@Bean
	public Docket sysDataInit(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("sysDataInit")
				.apiInfo(apiInfo())
				.select()
				.paths(sysDataInitPath())
				.build();
	}

	private Predicate<String> sysDataInitPath() {
		return or(
				regex("/sysDataInit/*.*"));
	}
	
	/**
	 * 
	 * @Description: 财务管理/应收账款
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月2日 下午5:52:04 
	 * @return 
	 * @return Docket
	 */
	@Bean
	public Docket finaceReceiveableAccountsInit(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("finaceAccounts")
				.apiInfo(apiInfo())
				.select()
				.paths(finaceReceiveableAccountsPath())
				.build();
	}

	private Predicate<String> finaceReceiveableAccountsPath() {
		return or(
				regex("/finance/accounts/*.*"));
	}

	/**
	 *
	 * @Description: 财务管理/应收缴款
	 * @author yuting.li
	 * @version 1.0
	 * @date 2018年1月2日 下午5:52:04
	 * @return
	 * @return Docket
	 */
	@Bean
	public Docket finaceReceiveableShoulepaymentInit(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("finaceShoulepayment")
				.apiInfo(apiInfo())
				.select()
				.paths(finaceReceiveableShoulepaymentPath())
				.build();
	}

	private Predicate<String> finaceReceiveableShoulepaymentPath() {
		return or(
				regex("/finance/shoulepayment/*.*"));
	}


	/** 
	* @Description: 财务报表-库房管理
	* @return:  
	* @Author: dongyang.du
	* @Date: 11/01/2018 
	*/ 
	@Bean
	public Docket finaceStockInit(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("report_finace_stock")
				.apiInfo(apiInfo())
				.select()
				.paths(finaceStockPath())
				.build();
	}

	private Predicate<String> finaceStockPath() {
		return or(
				regex("/report/finance/stock/*.*"));
	}

	/**
	 * @Description: 财务报表-库房管理
	 * @return:
	 * @Author: dongyang.du
	 * @Date: 11/01/2018
	 */
	@Bean
	public Docket finacePaymentVoucher(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("finance_payment_voucher")
				.apiInfo(apiInfo())
				.select()
				.paths(paymentVoucherPath())
				.build();
	}

	private Predicate<String> paymentVoucherPath() {
		return or(
				regex("/paymentVoucher/*.*"));
	}

	@Bean
	public Docket financeTest(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("finance_test")
				.apiInfo(apiInfo())
				.select()
				.paths(financeTestPath())
				.build();
	}

	private Predicate<String> financeTestPath() {
		return or(
				regex("/finance/test/*.*"));
	}

}
