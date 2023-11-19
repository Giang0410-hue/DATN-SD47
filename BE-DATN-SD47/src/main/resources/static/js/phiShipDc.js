
document.addEventListener('DOMContentLoaded', function () {
    var citySelect = document.getElementById('citySelect');
    var districtSelect = document.getElementById('districtSelect');
    var wardSelect = document.getElementById('wardSelect');
    console.log(citySelect)
    console.log(districtSelect)
    console.log(wardSelect)
    var selectedCityValue = /*[[ ${hoaDon.thanhPho} ]]*/ '';
    var selectedDistrictValue = /*[[ ${hoaDon.quanHuyen} ]]*/ '';
    var selectedWardValue = /*[[ ${hoaDon.phuongXa} ]]*/ '';

    fetch('https://online-gateway.ghn.vn/shiip/public-api/master-data/province', {
        method: 'GET',
        headers: {
            'token': 'd900c67f-742d-11ee-96dc-de6f804954c9',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            // Xử lý dữ liệu khi API trả về thành công
            populateSelect(citySelect, data.data, 'ProvinceID', 'ProvinceName');

            // Tìm option có giá trị là selectedCityValue và đặt nó làm được chọn
            var selectedOption = citySelect.querySelector(`option[value="${selectedCityValue}"]`);
            if (selectedOption) {
                selectedOption.selected = true;
                // Kiểm tra nếu giá trị thành phố đã được chọn
                if (selectedCityValue) {
                    // Gọi hàm để tải và populate quận/huyện dựa trên ID của thành phố
                    populateDistricts(selectedCityValue);
                }
            }

        })
        .catch(error => {
            console.error('Lỗi khi tải dữ liệu thành phố:', error);
        });

    // Sự kiện khi chọn thành phố
    citySelect.addEventListener('change', function () {
        // Lấy ID của thành phố đã chọn
        var selectedCityId = this.value;

        console.log(selectedCityId);
        // Kiểm tra nếu giá trị thành phố đã được chọn
        if (selectedCityId) {
            // Xóa hết các quận/huyện hiện tại trước khi tải mới
            districtSelect.innerHTML = '<option value="">Chọn quận/huyện</option>';
            // Gọi hàm để tải và populate quận/huyện dựa trên ID của thành phố
            populateDistricts(selectedCityId);
        }
    });

    // Sự kiện khi chọn thành phố
    districtSelect.addEventListener('change', function () {
        // Lấy ID của thành phố đã chọn
        var selectedDistrictId = this.value;

        console.log(selectedDistrictId);
        // Kiểm tra nếu giá trị thành phố đã được chọn
        if (selectedDistrictId) {
            // Xóa hết các quận/huyện hiện tại trước khi tải mới
            wardSelect.innerHTML = '<option value="">Chọn xã/phường</option>';
            // Gọi hàm để tải và populate quận/huyện dựa trên ID của thành phố
            populateWard(selectedDistrictId);
        }
    });

    // Hàm để tải và populate quận/huyện
    function populateDistricts(selectedCityId) {
        fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=${selectedCityId}`, {
            method: 'GET',
            headers: {
                'token': 'd900c67f-742d-11ee-96dc-de6f804954c9',
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                // Xử lý dữ liệu khi API trả về thành công
                populateSelect(districtSelect, data.data, 'DistrictID', 'DistrictName');

                // Tìm option có giá trị là selectedCityValue và đặt nó làm được chọn
                var selectedOption = districtSelect.querySelector(`option[value="${selectedDistrictValue}"]`);
                if (selectedOption) {
                    selectedOption.selected = true;
                    // Kiểm tra nếu giá trị thành phố đã được chọn
                    if (selectedDistrictValue) {
                        // Gọi hàm để tải và populate quận/huyện dựa trên ID của thành phố
                        populateWard(selectedDistrictValue);
                    }
                }

            })
            .catch(error => {
                console.error('Lỗi khi tải dữ liệu quận:', error);
            });
    }

    // Hàm để tải và populate xã phường
    function populateWard(selectedDistrictId) {
        fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=${selectedDistrictId}`, {
            method: 'GET',
            headers: {
                'token': 'd900c67f-742d-11ee-96dc-de6f804954c9',
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                // Xử lý dữ liệu khi API trả về thành công
                populateSelect(wardSelect, data.data, 'WardCode', 'WardName');

                // Tìm option có giá trị là selectedCityValue và đặt nó làm được chọn
                var selectedOption = wardSelect.querySelector(`option[value="${selectedWardValue}"]`);
                if (selectedOption) {
                    selectedOption.selected = true;
                    // Kiểm tra nếu giá trị thành phố đã được chọn
                    // if (selectedWardValue) {
                    //     // Gọi hàm để tải và populate quận/huyện dựa trên ID của thành phố
                    //     populateWard(selectedWardValue);
                    // }
                }

            })
            .catch(error => {
                console.error('Lỗi khi tải dữ liệu quận:', error);
            });
    }

    // Hàm để populate dropdown
    function populateSelect(select, data, idKey, textKey) {
        // select.innerHTML = '<option value="">Chọn thành phố</option>';
        data.forEach(item => {
            var option = document.createElement('option');
            option.value = item[idKey];
            option.text = item[textKey];
            select.appendChild(option);
        });
    }
});
function calculateShippingFee() {
    var citySelect = document.getElementById('citySelect').value;
    var districtSelect = document.getElementById('districtSelect').value;
    var wardSelect = document.getElementById('wardSelect').value;
    const shippingInfo = {
        // weight: parseFloat(weight),
        service_type_id: 2,
        // from_district_id: 3440,
        to_district_id: Number(districtSelect),
        to_ward_code: wardSelect,
        height: 30, //cao cm
        length: 30, //chieu dai cm
        weight: 1000, //can nang (g)
        width: 30, //rong cm
        insurance_value: 0,
        coupon: null,
        items: [
            {
                "name": "TEST1",
                "quantity": 1,
                "height": 20,
                "weight": 1,
                "length": 20,
                "width": 20
            }
        ]
    };
    console.log(shippingInfo)

    fetch('https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Token': '0c4a5894-7fc8-11ee-96dc-de6f804954c9', // Replace with your GHN API token
            // 'shop_id': 4689281
        },
        body: JSON.stringify(shippingInfo)
    })
        .then(response => response.json())
        .then(data => {
            const shippingFeeResult = document.getElementById('shippingFeeResult');
            shippingFeeResult.value = `${data.data.total}`; // Thay đổi ở đây
            const shippingFeeResult2 = document.getElementById('phi-van-chuyen');
            shippingFeeResult2.innerHTML = `${data.data.total} VND`;
            console.log(data)
            const phiShipInput = document.getElementById('shippingFeeResult');

            // Lấy thẻ <h6> có class là tong-gia
            const tongGiaElement = document.querySelector('.tong-gia');
            const shippingFeeResultInput = document.getElementById('shippingFeeResult');
            const tongTienHoaDonInput = document.querySelector('input[name="tongTienHoaDon"]');
            const tongGiaValue = document.getElementById('tongGiaValue');
            const tongAll = document.getElementById('tongAll');
            // Hàm cộng giá trị từ hai ô input và cập nhật nội dung của thẻ <h6>
            function congHaiInputVaCapNhatTongGia() {
                // Lấy giá trị từ hai ô input
                const tongTienHoaDon = parseFloat(tongTienHoaDonInput.value);
                const shippingFee = parseFloat(shippingFeeResultInput.value);
        
                // Kiểm tra nếu ô input shippingFee không có giá trị hợp lệ thì gán giá trị mặc định là 0
                const phiShip = isNaN(shippingFee) ? 0 : shippingFee;
        
                // Thực hiện phép cộng
                const tongGia = tongTienHoaDon + phiShip;
        
                // Hiển thị kết quả trong thẻ <h6>
                tongGiaValue.textContent = tongGia.toFixed(0); // Giữ hai chữ số sau dấu thập phân
                tongAll.value = tongGia.toFixed(0);
            }
        
            // Gọi hàm khi giá trị trong ô input thay đổi
            shippingFeeResultInput.addEventListener('input', congHaiInputVaCapNhatTongGia);
            tongTienHoaDonInput.addEventListener('input', congHaiInputVaCapNhatTongGia);
        
            // Gọi hàm lần đầu để hiển thị kết quả ban đầu
            congHaiInputVaCapNhatTongGia();
            

        })
        .catch(error => console.error('Error:', error));
}


