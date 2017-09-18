//使用ajax加载数据字典,生成select
//参数1: 数据字典类型 (dict_type_code)
//参数2: 将下啦选放入的标签id
//参数3: 生成下拉选时,select标签的name属性值
//参数4: 需要回显时,选中哪个option
//使用ajax加载数据字典，生成select
function loadSelect(typeCode,positionId,selectName,selectedId) {
    //创建select对象，将name属性指定
    var $select= $("<select name='"+selectName+"'></seclet>");
    //添加提示选项
    $select.append($("<option value=''>---请选择---</option>"));
    //使用jquery的ajax方法，访问后台Action
    $.post(
        "${pageContext.request.contextPath}/BaseDictAction",
        {dict_type_code:typeCode},
        function (data) {
            //返回Json数组对象，对其遍历
            $.each(data,function (i, json) {
                var $option = $("<option value='"+json['dict_id']+"'>"+json["dict_item_name"]+"</option>")
                if(json['dict_id'] == selectedId){
                    //判断是否需要回显
                    $option.attr("selected","selected");
                }

                $select.append($option);
            });
        },
        "json"

    );

    //将组装好的select对象放入页面指定位置
    $("#"+positionId).append($select);
}