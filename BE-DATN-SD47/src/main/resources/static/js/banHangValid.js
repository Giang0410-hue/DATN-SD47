
function validatePhoneNumber(phoneNumber) {
    // Biểu thức chính quy để kiểm tra số điện thoại cơ bản
    var phonePattern = /((09|03|07|08|05)+([0-9]{8})\b)/g;

    // Kiểm tra xem số điện thoại có khớp với pattern không
    console.log(phoneNumber)
    return phonePattern.test(phoneNumber);
}
function validateAndSubmit(slg, sl, idCtsp) {

    var soLuong = document.getElementById('soLuongEdit' + idCtsp).value;
    var soLuongGoc = slg;
    if (isNaN(soLuong) || soLuong < 0) {
        alert("Vui lòng nhập một số nguyên không âm.");
    } else if (parseInt(soLuong) > parseInt(soLuongGoc)) {
        alert("Số lượng không được lớn hơn số lượng hiện tại (" + soLuongGoc + ").");
    } else if (soLuong === "") {
        alert("Vui lòng nhập số lượng");
    } else {
        // alert("Số lượng hợp lệ: " + soLuong);

        // Nếu số lượng hợp lệ, thực hiện submit form
        document.getElementById("myForm" + idCtsp).submit();
    }
}

function validateAndSubmitTraHang(slg, sl, idCtsp) {

    var soLuong = document.getElementById('soLuongEditTra' + idCtsp).value;
    var soLuongGoc = slg;
    if (isNaN(soLuong) || soLuong < 0) {
        alert("Vui lòng nhập một số nguyên không âm.");
    } else if (parseInt(soLuong) > parseInt(soLuongGoc)) {
        alert("Số lượng không được lớn hơn số lượng hiện tại (" + soLuongGoc + ").");
    } else if (soLuong === "") {
        alert("Vui lòng nhập số lượng trả");
    } else {
        alert("Số lượng hợp lệ: " + soLuong);

        // Nếu số lượng hợp lệ, thực hiện submit form
        document.getElementById("myForm" + idCtsp).submit();
    }
}
function validGhiChuThanhToan() {
    var textarea = document.getElementById("nhapGhiChu");
    var text = textarea.value.trim(); // Lấy giá trị và loại bỏ khoảng trắng
    var errorGhiChu = document.getElementById("errorGhiChuThanhToan")
    if (text === '') {
        errorGhiChu.innerHTML = "Vui lòng nhập ghi chú";

        alert('s')
        return false;
    } else {
        alert('vd')
    }
}
function validateThanhToan() {

    var inputGhiChuThanhToan = document.getElementById('nhapGhiChu')
    var errorGhiChu = document.getElementById("errorGhiChuThanhToan")

    if (inputGhiChuThanhToan.value.trim() === '') {
        errorGhiChu.innerHTML = "Vui lòng nhập ghi chú";

    
        return false;
    } 
    // return false;
    handleButtonClick('thanhToan');

    const checkGiaoHang = document.getElementById('toggleVisibility').checked

    const inputHoVaTen = document.getElementById('inputHoVaTen').value
    const inputSoDienThoai = document.getElementById('inputSoDienThoai').value
    const citySelect = document.getElementById('citySelect').value
    const districtSelect = document.getElementById('districtSelect').value
    const wardSelect = document.getElementById('wardSelect').value
    const inputDiaChiCuThe = document.getElementById('inputDiaChiCuThe').value

    const errorInputDiaChiCuThe = document.getElementById('errorInputDiaChiCuThe')
    const errorInputHoVaTen = document.getElementById('errorInputHoVaTen')
    const errorWardSelect = document.getElementById('errorWardSelect')
    const errorInputSoDienThoai = document.getElementById('errorInputSoDienThoai')
    const errorDistrictSelect = document.getElementById('errorDistrictSelect')
    const errorCitySelect = document.getElementById('errorCitySelect')
    var checkTong = true;
    if (checkGiaoHang) {
        if (inputHoVaTen === '') {
            errorInputHoVaTen.innerHTML = 'Vui lòng nhập họ tên';
            checkTong = false;
        } else {
            errorInputHoVaTen.innerHTML = '';
        }
        if (inputDiaChiCuThe === '') {
            errorInputDiaChiCuThe.innerHTML = 'Vui lòng nhập địa chỉ cụ thể';
            checkTong = false;
        } else {
            errorInputDiaChiCuThe.innerHTML = '';
        }
        if (inputSoDienThoai === '') {
            errorInputSoDienThoai.innerHTML = 'Vui lòng nhập số điện thoại';
            checkTong = false;

        } else if (!validatePhoneNumber(inputSoDienThoai)) {
            checkTong = false;
            errorInputSoDienThoai.innerHTML = 'Số điện thoại không đúng định dạng';
        } else {
            errorInputSoDienThoai.innerHTML = '';
        }



        if (citySelect === '') {
            errorCitySelect.innerHTML = 'Vui lòng chọn thành phố';
            checkTong = false;
        } else {
            errorCitySelect.innerHTML = '';
        }
        if (districtSelect === '') {
            errorDistrictSelect.innerHTML = 'Vui lòng chọn quận/huyện';
            checkTong = false;
        } else {
            errorDistrictSelect.innerHTML = '';
        }
        if (wardSelect === '') {
            errorWardSelect.innerHTML = 'Vui lòng chọn phường/xã';
            checkTong = false;
        } else {
            errorWardSelect.innerHTML = '';
        }

    }

    return checkTong;


}

function handleButtonClick(idBtn) {
    var button = document.getElementById(idBtn); // Lấy button
    console.log('handleButtonClick')
    if (button.getAttribute('disabled') === 'disabled') {
        // Nếu button đã được vô hiệu hóa, không làm gì cả (để ngăn chặn spam)
        return;
    }

    // Vô hiệu hóa button sau khi được nhấn
    button.setAttribute('disabled', 'disabled');

    // Gửi yêu cầu hoặc thực hiện công việc cần thiết ở đây

    // Sau một khoảng thời gian, có thể muốn kích hoạt lại button (nếu thích)
    setTimeout(function () {
        button.removeAttribute('disabled'); // Kích hoạt lại button sau một khoảng thời gian
    }, 1500); // Ví dụ: kích hoạt lại sau 3 giây (3000 milliseconds)
}

const inputElement = document.getElementById('tienKhachTra'); // Thay 'inputId' bằng ID thực của ô input
const tongTien = document.getElementById('tongAll');
const traLai = document.getElementById('traLai');
inputElement.addEventListener('input', function (event) {
    const newValue = event.target.value;
    traLai.innerHTML = (newValue - tongTien.value)

    // Thực hiện các hành động khác ngay khi giá trị thay đổi
});

function resetTienKhachTra() {
    const inputElement = document.getElementById('tienKhachTra'); // Thay 'inputId' bằng ID thực của ô input
    const tongTien = document.getElementById('tongAll');
    const traLai = document.getElementById('traLai');
    inputElement.value = null
    traLai.innerHTML = '0'
}
function resetTienKhachTra2() {
    const inputElement2 = document.getElementById('tongAll'); // Thay 'inputId' bằng ID thực của ô input

    inputElement2.addEventListener('input', function (event) {
        const newValue = event.target.value;
        console.log('Giá trị mới tt:', newValue);

        // Thực hiện các hành động khác sau khi giá trị thay đổi
    });
    const inputElement = document.getElementById('tienKhachTra'); // Thay 'inputId' bằng ID thực của ô input
    const tongTien = document.getElementById('tongAll');
    const traLai = document.getElementById('traLai');
    inputElement.value = null
    traLai.innerHTML = '0'
}

function checkValidGhiChu() {
    var textarea = document.getElementById("ghiChu");
    var text = textarea.value.trim(); // Lấy giá trị và loại bỏ khoảng trắng
    var errorGhiChu = document.getElementById("errorGhiChu")
    if (text === '') {
        errorGhiChu.innerHTML = "Vui lòng nhập ghi chú";


        return false;
    } else {
        return true;
    }
}

function getNameXaPhuong(idQuanHuyen,idXaPhuong) {
    fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=${idQuanHuyen}`, {
        method: 'GET',
        headers: {
            'token': 'd900c67f-742d-11ee-96dc-de6f804954c9',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            // Xử lý dữ liệu khi API trả về thành công
            // populateSelect(wardSelect, data.data, 'WardCode', 'WardName');
            data.data.forEach(x=>{
                if(x.WardCode===idXaPhuong){
                    console.log(x.WardName)
                    document.getElementById('phuongXaChonDc').innerHTML = x.WardName
                }
            })
            // console.log(data.data)

        })
        .catch(error => {
            console.error('Lỗi khi tải dữ liệu quận:', error);
        });
}

function getNameQuanHuyenById(idThanhPho, idQuanHuyen) {
    fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=${idThanhPho}`, {
        method: 'GET',
        headers: {
            'token': 'd900c67f-742d-11ee-96dc-de6f804954c9',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            // Xử lý dữ liệu khi API trả về thành công
            data.data.forEach(q => {
                if (q.DistrictID === idQuanHuyen) {
                    console.log(q.DistrictName)
                }
            });
            // console.log(data.data)


        })
        .catch(error => {
            console.error('Lỗi khi tải dữ liệu quận:', error);
        });
}

function getNameThanhPhoById(idThanhPho) {
    var tenThanhPho = ''

    fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/province`, {
        method: 'GET',
        headers: {
            'token': 'd900c67f-742d-11ee-96dc-de6f804954c9',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            // Xử lý dữ liệu khi API trả về thành công
            var lst = data.data
            lst.forEach(dc => {
                // console.log(dc.ProvinceID)
                if (idThanhPho === dc.ProvinceID) {
                    tenThanhPho = dc.ProvinceName
                    console.log(tenThanhPho)
                }


            });


        })
        .catch(error => {
            console.error('Lỗi khi tải dữ liệu quận:', error);
        });

}
