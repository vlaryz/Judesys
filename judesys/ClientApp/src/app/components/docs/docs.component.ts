import { Component, OnInit } from '@angular/core';

declare let Redoc: any


@Component({
  selector: 'app-docs',
  templateUrl: './docs.component.html',
  styleUrls: ['./docs.component.css']
})
export class DocsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    this.initDocs();
  }

  initDocs() {
    Redoc.init('http://localhost:8080/v2/api-docs?group=public-api', {
      hideDownloadButton: true,
    }, document.getElementById('redoc'))
  }

}
