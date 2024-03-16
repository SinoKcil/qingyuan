package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.model.Abnormality;
import com.example.qingyuanbackend.service.AbnormalityService;
import com.example.qingyuanbackend.utils.AbnormalityDetail;
import com.example.qingyuanbackend.utils.AbnormalityForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Abnormalities", description = "工厂地图")
public class AbnormalityController {

    @Autowired
    private AbnormalityService abnormalityService;

    @GetMapping("/region")
    @Operation(summary = "获取指定区域和层级的异常情况",
            description = "根据请求的区域名称和层级，返回该区域在指定层级上的所有异常情况。" +
                    "层级参数是可选的，默认为1。",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AbnormalityForm.class))),
            })
    public ResponseEntity<?> getAbnormalitiseByRegionAndLayer(
            @RequestParam @Parameter(description = "区域名称，如'Shanghai'") String region,
            @RequestParam(defaultValue = "1") @Parameter(description = "层级，整数，默认为1") Integer layer
    ){
        List<Abnormality> abnormalities = abnormalityService.getAbnormalitiesByRegionAndLayer(region, layer);
        AbnormalityForm form = abnormalityService.transformAbnormalitiesToForm(abnormalities, region, layer);
        return ResponseEntity.ok(form);
    }

    @GetMapping("/detail")
    @Operation(summary = "获取异常详情",
            description = "根据区域名称、层级、行号、列号以及标签来查询具体的异常详情。" +
                    "这里的行号和列号对应前端渲染的表格中的位置，标签用于指定异常类型。",
            parameters = {
                    @Parameter(name = "region", description = "区域名称，例如'Shanghai'", required = true),
                    @Parameter(name = "layer", description = "异常数据所在的层级，整数，默认为1", required = true),
                    @Parameter(name = "row", description = "异常数据所在的行号，对应于前端表格的行", required = true),
                    @Parameter(name = "col", description = "异常数据所在的列号，对应于前端表格的列", required = true),
                    @Parameter(name = "label", description = "异常的标签，用于区分不同类型的异常", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "请求成功，返回异常详情",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AbnormalityDetail.class))),
            })
    public ResponseEntity<?> getAbnormalityDetail(
            @RequestParam String region,
            @RequestParam int layer,
            @RequestParam int row,
            @RequestParam int col,
            @RequestParam int label) {
        AbnormalityDetail detail = abnormalityService.getAbnormalityDetail(region, layer, row, col, label);
        return ResponseEntity.ok(detail);
    }

}
