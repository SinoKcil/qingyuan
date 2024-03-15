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
            description = "根据区域、层级、行、列的参数来获取异常详情。")
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
